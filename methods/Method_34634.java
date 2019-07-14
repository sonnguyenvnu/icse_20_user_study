@SuppressWarnings("unchecked") private RequestCollapser<BatchReturnType,ResponseType,RequestArgumentType> getCollapserForGlobalScope(HystrixCollapserBridge<BatchReturnType,ResponseType,RequestArgumentType> commandCollapser){
  RequestCollapser<?,?,?> collapser=globalScopedCollapsers.get(collapserKey.name());
  if (collapser != null) {
    return (RequestCollapser<BatchReturnType,ResponseType,RequestArgumentType>)collapser;
  }
  RequestCollapser<BatchReturnType,ResponseType,RequestArgumentType> newCollapser=new RequestCollapser<BatchReturnType,ResponseType,RequestArgumentType>(commandCollapser,properties,timer,concurrencyStrategy);
  RequestCollapser<?,?,?> existing=globalScopedCollapsers.putIfAbsent(collapserKey.name(),newCollapser);
  if (existing == null) {
    return newCollapser;
  }
 else {
    newCollapser.shutdown();
    return (RequestCollapser<BatchReturnType,ResponseType,RequestArgumentType>)existing;
  }
}
