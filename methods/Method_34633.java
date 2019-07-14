public RequestCollapser<BatchReturnType,ResponseType,RequestArgumentType> getRequestCollapser(HystrixCollapserBridge<BatchReturnType,ResponseType,RequestArgumentType> commandCollapser){
  if (Scopes.REQUEST == Scopes.valueOf(getScope().name())) {
    return getCollapserForUserRequest(commandCollapser);
  }
 else   if (Scopes.GLOBAL == Scopes.valueOf(getScope().name())) {
    return getCollapserForGlobalScope(commandCollapser);
  }
 else {
    logger.warn("Invalid Scope: {}  Defaulting to REQUEST scope.",getScope());
    return getCollapserForUserRequest(commandCollapser);
  }
}
