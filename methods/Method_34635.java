@SuppressWarnings("unchecked") private RequestCollapser<BatchReturnType,ResponseType,RequestArgumentType> getCollapserForUserRequest(HystrixCollapserBridge<BatchReturnType,ResponseType,RequestArgumentType> commandCollapser){
  return (RequestCollapser<BatchReturnType,ResponseType,RequestArgumentType>)getRequestVariableForCommand(commandCollapser).get(concurrencyStrategy);
}
