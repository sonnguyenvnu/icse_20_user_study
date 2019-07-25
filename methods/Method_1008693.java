/** 
 * In a package-private method so that it can be tested without having to wire everything (mapperService, etc.)
 * @return whether the rescoring phase should be executed
 */
static boolean execute(SearchContext searchContext,final IndexSearcher searcher,Consumer<Runnable> checkCancellationSetter) throws QueryPhaseExecutionException {
  final IndexReader reader=searcher.getIndexReader();
  QuerySearchResult queryResult=searchContext.queryResult();
  queryResult.searchTimedOut(false);
  try {
    queryResult.from(searchContext.from());
    queryResult.size(searchContext.size());
    Query query=searchContext.query();
    assert query == searcher.rewrite(query);
    final ScrollContext scrollContext=searchContext.scrollContext();
    if (scrollContext != null) {
      if (scrollContext.totalHits == -1) {
        assert scrollContext.lastEmittedDoc == null;
      }
 else {
        final ScoreDoc after=scrollContext.lastEmittedDoc;
        if (returnsDocsInOrder(query,searchContext.sort())) {
          if (after != null) {
            BooleanQuery bq=new BooleanQuery.Builder().add(query,BooleanClause.Occur.MUST).add(new MinDocQuery(after.doc + 1),BooleanClause.Occur.FILTER).build();
            query=bq;
          }
          searchContext.terminateAfter(searchContext.size());
          searchContext.trackTotalHits(false);
        }
 else         if (canEarlyTerminate(reader,searchContext.sort())) {
          if (after != null) {
            BooleanQuery bq=new BooleanQuery.Builder().add(query,BooleanClause.Occur.MUST).add(new SearchAfterSortedDocQuery(searchContext.sort().sort,(FieldDoc)after),BooleanClause.Occur.FILTER).build();
            query=bq;
          }
          searchContext.trackTotalHits(false);
        }
      }
    }
    final LinkedList<QueryCollectorContext> collectors=new LinkedList<>();
    boolean hasFilterCollector=false;
    if (searchContext.parsedPostFilter() != null) {
      collectors.add(createFilteredCollectorContext(searcher,searchContext.parsedPostFilter().query()));
      hasFilterCollector=true;
    }
    if (searchContext.queryCollectors().isEmpty() == false) {
      collectors.add(createMultiCollectorContext(searchContext.queryCollectors().values()));
    }
    if (searchContext.minimumScore() != null) {
      collectors.add(createMinScoreCollectorContext(searchContext.minimumScore()));
      hasFilterCollector=true;
    }
    if (searchContext.terminateAfter() != SearchContext.DEFAULT_TERMINATE_AFTER) {
      collectors.add(createEarlyTerminationCollectorContext(searchContext.terminateAfter()));
      hasFilterCollector=true;
    }
    boolean timeoutSet=scrollContext == null && searchContext.timeout() != null && searchContext.timeout().equals(SearchService.NO_TIMEOUT) == false;
    final Runnable timeoutRunnable;
    if (timeoutSet) {
      final Counter counter=searchContext.timeEstimateCounter();
      final long startTime=counter.get();
      final long timeout=searchContext.timeout().millis();
      final long maxTime=startTime + timeout;
      timeoutRunnable=() -> {
        final long time=counter.get();
        if (time > maxTime) {
          throw new TimeExceededException();
        }
      }
;
    }
 else {
      timeoutRunnable=null;
    }
    final Runnable cancellationRunnable;
    if (searchContext.lowLevelCancellation()) {
      SearchTask task=searchContext.getTask();
      cancellationRunnable=() -> {
        if (task.isCancelled())         throw new TaskCancelledException("cancelled");
      }
;
    }
 else {
      cancellationRunnable=null;
    }
    final Runnable checkCancelled;
    if (timeoutRunnable != null && cancellationRunnable != null) {
      checkCancelled=() -> {
        timeoutRunnable.run();
        cancellationRunnable.run();
      }
;
    }
 else     if (timeoutRunnable != null) {
      checkCancelled=timeoutRunnable;
    }
 else     if (cancellationRunnable != null) {
      checkCancelled=cancellationRunnable;
    }
 else {
      checkCancelled=null;
    }
    checkCancellationSetter.accept(checkCancelled);
    collectors.add(createCancellableCollectorContext(searchContext.getTask()::isCancelled));
    final boolean doProfile=searchContext.getProfilers() != null;
    final TopDocsCollectorContext topDocsFactory=createTopDocsCollectorContext(searchContext,reader,hasFilterCollector);
    collectors.addFirst(topDocsFactory);
    final Collector queryCollector;
    if (doProfile) {
      InternalProfileCollector profileCollector=QueryCollectorContext.createQueryCollectorWithProfiler(collectors);
      searchContext.getProfilers().getCurrentQueryProfiler().setCollector(profileCollector);
      queryCollector=profileCollector;
    }
 else {
      queryCollector=QueryCollectorContext.createQueryCollector(collectors);
    }
    try {
      searcher.search(query,queryCollector);
    }
 catch (    TimeExceededException e) {
      assert timeoutSet : "TimeExceededException thrown even though timeout wasn't set";
      queryResult.searchTimedOut(true);
    }
 finally {
      searchContext.clearReleasables(SearchContext.Lifetime.COLLECTION);
    }
    final QuerySearchResult result=searchContext.queryResult();
    for (    QueryCollectorContext ctx : collectors) {
      ctx.postProcess(result);
    }
    EsThreadPoolExecutor executor=(EsThreadPoolExecutor)searchContext.indexShard().getThreadPool().executor(ThreadPool.Names.SEARCH);
    if (executor instanceof QueueResizingEsThreadPoolExecutor) {
      QueueResizingEsThreadPoolExecutor rExecutor=(QueueResizingEsThreadPoolExecutor)executor;
      queryResult.nodeQueueSize(rExecutor.getCurrentQueueSize());
      queryResult.serviceTimeEWMA((long)rExecutor.getTaskExecutionEWMA());
    }
    if (searchContext.getProfilers() != null) {
      ProfileShardResult shardResults=SearchProfileShardResults.buildShardResults(searchContext.getProfilers());
      result.profileResults(shardResults);
    }
    return topDocsFactory.shouldRescore();
  }
 catch (  Exception e) {
    throw new QueryPhaseExecutionException(searchContext,"Failed to execute main query",e);
  }
}
