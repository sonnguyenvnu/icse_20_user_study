public Map<StaticBuffer,EntryList> edgeStoreMultiQuery(final List<StaticBuffer> keys,final SliceQuery query){
  if (storeFeatures.hasMultiQuery()) {
    return executeRead(new Callable<Map<StaticBuffer,EntryList>>(){
      @Override public Map<StaticBuffer,EntryList> call() throws Exception {
        return cacheEnabled ? edgeStore.getSlice(keys,query,storeTx) : edgeStore.getSliceNoCache(keys,query,storeTx);
      }
      @Override public String toString(){
        return "MultiEdgeStoreQuery";
      }
    }
);
  }
 else {
    final Map<StaticBuffer,EntryList> results=new HashMap<>(keys.size());
    if (threadPool == null || keys.size() < MIN_TASKS_TO_PARALLELIZE) {
      for (      StaticBuffer key : keys) {
        results.put(key,edgeStoreQuery(new KeySliceQuery(key,query)));
      }
    }
 else {
      final CountDownLatch doneSignal=new CountDownLatch(keys.size());
      final AtomicInteger failureCount=new AtomicInteger(0);
      EntryList[] resultArray=new EntryList[keys.size()];
      for (int i=0; i < keys.size(); i++) {
        threadPool.execute(new SliceQueryRunner(new KeySliceQuery(keys.get(i),query),doneSignal,failureCount,resultArray,i));
      }
      try {
        doneSignal.await();
      }
 catch (      InterruptedException e) {
        throw new JanusGraphException("Interrupted while waiting for multi-query to complete",e);
      }
      if (failureCount.get() > 0) {
        throw new JanusGraphException("Could not successfully complete multi-query. " + failureCount.get() + " individual queries failed.");
      }
      for (int i=0; i < keys.size(); i++) {
        assert resultArray[i] != null;
        results.put(keys.get(i),resultArray[i]);
      }
    }
    return results;
  }
}
