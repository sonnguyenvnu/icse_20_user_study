/** 
 * Lookup (or create and store) the RequestVariable for a given HystrixCollapserKey.
 * @param commandCollapser collapser to retrieve {@link HystrixRequestVariableHolder} for
 * @return HystrixRequestVariableHolder
 */
@SuppressWarnings("unchecked") private HystrixRequestVariableHolder<RequestCollapser<?,?,?>> getRequestVariableForCommand(final HystrixCollapserBridge<BatchReturnType,ResponseType,RequestArgumentType> commandCollapser){
  HystrixRequestVariableHolder<RequestCollapser<?,?,?>> requestVariable=requestScopedCollapsers.get(commandCollapser.getCollapserKey().name());
  if (requestVariable == null) {
    @SuppressWarnings({"rawtypes"}) HystrixRequestVariableHolder newCollapser=new RequestCollapserRequestVariable(commandCollapser,properties,timer,concurrencyStrategy);
    HystrixRequestVariableHolder<RequestCollapser<?,?,?>> existing=requestScopedCollapsers.putIfAbsent(commandCollapser.getCollapserKey().name(),newCollapser);
    if (existing == null) {
      requestVariable=newCollapser;
    }
 else {
      requestVariable=existing;
    }
  }
  return requestVariable;
}
