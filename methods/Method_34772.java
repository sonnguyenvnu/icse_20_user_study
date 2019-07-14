public Map<ExecutionSignature,List<Integer>> getExecutionsMappedToLatencies(){
  Map<CommandAndCacheKey,Integer> cachingDetector=new HashMap<CommandAndCacheKey,Integer>();
  List<HystrixInvokableInfo<?>> nonCachedExecutions=new ArrayList<HystrixInvokableInfo<?>>(executions.size());
  for (  HystrixInvokableInfo<?> execution : executions) {
    if (execution.getPublicCacheKey() != null) {
      CommandAndCacheKey key=new CommandAndCacheKey(execution.getCommandKey().name(),execution.getPublicCacheKey());
      Integer count=cachingDetector.get(key);
      if (count != null) {
        cachingDetector.put(key,count + 1);
      }
 else {
        cachingDetector.put(key,0);
      }
    }
    if (!execution.isResponseFromCache()) {
      nonCachedExecutions.add(execution);
    }
  }
  Map<ExecutionSignature,List<Integer>> commandDeduper=new HashMap<ExecutionSignature,List<Integer>>();
  for (  HystrixInvokableInfo<?> execution : nonCachedExecutions) {
    int cachedCount=0;
    String cacheKey=execution.getPublicCacheKey();
    if (cacheKey != null) {
      CommandAndCacheKey key=new CommandAndCacheKey(execution.getCommandKey().name(),cacheKey);
      cachedCount=cachingDetector.get(key);
    }
    ExecutionSignature signature;
    if (cachedCount > 0) {
      signature=ExecutionSignature.from(execution,cacheKey,cachedCount);
    }
 else {
      signature=ExecutionSignature.from(execution);
    }
    List<Integer> currentLatencyList=commandDeduper.get(signature);
    if (currentLatencyList != null) {
      currentLatencyList.add(execution.getExecutionTimeInMilliseconds());
    }
 else {
      List<Integer> newLatencyList=new ArrayList<Integer>();
      newLatencyList.add(execution.getExecutionTimeInMilliseconds());
      commandDeduper.put(signature,newLatencyList);
    }
  }
  return commandDeduper;
}
