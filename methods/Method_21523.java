@Override protected List<SearchHit> innerRun() throws SqlParseException {
  List<SearchHit> combinedResults=new ArrayList<>();
  int totalLimit=nestedLoopsRequest.getTotalLimit();
  int multiSearchMaxSize=nestedLoopsRequest.getMultiSearchMaxSize();
  Select secondTableSelect=nestedLoopsRequest.getSecondTable().getOriginalSelect();
  Where originalSecondTableWhere=secondTableSelect.getWhere();
  orderConditions(nestedLoopsRequest.getFirstTable().getAlias(),nestedLoopsRequest.getSecondTable().getAlias());
  FetchWithScrollResponse fetchWithScrollResponse=firstFetch(this.nestedLoopsRequest.getFirstTable());
  SearchResponse firstTableResponse=fetchWithScrollResponse.getResponse();
  boolean needScrollForFirstTable=fetchWithScrollResponse.isNeedScrollForFirstTable();
  int currentCombinedResults=0;
  boolean finishedWithFirstTable=false;
  while (totalLimit > currentCombinedResults && !finishedWithFirstTable) {
    SearchHit[] hits=firstTableResponse.getHits().getHits();
    boolean finishedMultiSearches=hits.length == 0;
    int currentHitsIndex=0;
    while (!finishedMultiSearches) {
      MultiSearchRequest multiSearchRequest=createMultiSearchRequest(multiSearchMaxSize,nestedLoopsRequest.getConnectedWhere(),hits,secondTableSelect,originalSecondTableWhere,currentHitsIndex);
      int multiSearchSize=multiSearchRequest.requests().size();
      currentCombinedResults=combineResultsFromMultiResponses(combinedResults,totalLimit,currentCombinedResults,hits,currentHitsIndex,multiSearchRequest);
      currentHitsIndex+=multiSearchSize;
      finishedMultiSearches=currentHitsIndex >= hits.length - 1 || currentCombinedResults >= totalLimit;
    }
    if (hits.length < MAX_RESULTS_ON_ONE_FETCH)     needScrollForFirstTable=false;
    if (!finishedWithFirstTable) {
      if (needScrollForFirstTable)       firstTableResponse=client.prepareSearchScroll(firstTableResponse.getScrollId()).setScroll(new TimeValue(600000)).get();
 else       finishedWithFirstTable=true;
    }
  }
  return combinedResults;
}
