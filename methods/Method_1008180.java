public void add(CommonStats stats){
  if (docs == null) {
    if (stats.getDocs() != null) {
      docs=new DocsStats();
      docs.add(stats.getDocs());
    }
  }
 else {
    docs.add(stats.getDocs());
  }
  if (store == null) {
    if (stats.getStore() != null) {
      store=new StoreStats();
      store.add(stats.getStore());
    }
  }
 else {
    store.add(stats.getStore());
  }
  if (indexing == null) {
    if (stats.getIndexing() != null) {
      indexing=new IndexingStats();
      indexing.add(stats.getIndexing());
    }
  }
 else {
    indexing.add(stats.getIndexing());
  }
  if (get == null) {
    if (stats.getGet() != null) {
      get=new GetStats();
      get.add(stats.getGet());
    }
  }
 else {
    get.add(stats.getGet());
  }
  if (search == null) {
    if (stats.getSearch() != null) {
      search=new SearchStats();
      search.add(stats.getSearch());
    }
  }
 else {
    search.add(stats.getSearch());
  }
  if (merge == null) {
    if (stats.getMerge() != null) {
      merge=new MergeStats();
      merge.add(stats.getMerge());
    }
  }
 else {
    merge.add(stats.getMerge());
  }
  if (refresh == null) {
    if (stats.getRefresh() != null) {
      refresh=new RefreshStats();
      refresh.add(stats.getRefresh());
    }
  }
 else {
    refresh.add(stats.getRefresh());
  }
  if (flush == null) {
    if (stats.getFlush() != null) {
      flush=new FlushStats();
      flush.add(stats.getFlush());
    }
  }
 else {
    flush.add(stats.getFlush());
  }
  if (warmer == null) {
    if (stats.getWarmer() != null) {
      warmer=new WarmerStats();
      warmer.add(stats.getWarmer());
    }
  }
 else {
    warmer.add(stats.getWarmer());
  }
  if (queryCache == null) {
    if (stats.getQueryCache() != null) {
      queryCache=new QueryCacheStats();
      queryCache.add(stats.getQueryCache());
    }
  }
 else {
    queryCache.add(stats.getQueryCache());
  }
  if (fieldData == null) {
    if (stats.getFieldData() != null) {
      fieldData=new FieldDataStats();
      fieldData.add(stats.getFieldData());
    }
  }
 else {
    fieldData.add(stats.getFieldData());
  }
  if (completion == null) {
    if (stats.getCompletion() != null) {
      completion=new CompletionStats();
      completion.add(stats.getCompletion());
    }
  }
 else {
    completion.add(stats.getCompletion());
  }
  if (segments == null) {
    if (stats.getSegments() != null) {
      segments=new SegmentsStats();
      segments.add(stats.getSegments());
    }
  }
 else {
    segments.add(stats.getSegments());
  }
  if (translog == null) {
    if (stats.getTranslog() != null) {
      translog=new TranslogStats();
      translog.add(stats.getTranslog());
    }
  }
 else {
    translog.add(stats.getTranslog());
  }
  if (requestCache == null) {
    if (stats.getRequestCache() != null) {
      requestCache=new RequestCacheStats();
      requestCache.add(stats.getRequestCache());
    }
  }
 else {
    requestCache.add(stats.getRequestCache());
  }
  if (recoveryStats == null) {
    if (stats.getRecoveryStats() != null) {
      recoveryStats=new RecoveryStats();
      recoveryStats.add(stats.getRecoveryStats());
    }
  }
 else {
    recoveryStats.add(stats.getRecoveryStats());
  }
}
