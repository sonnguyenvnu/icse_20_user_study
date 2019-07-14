private static RequestFilterAction processFilters(Request request,List<RequestFilter> requestFilters,RequestFilterAction lastAction){
  if (requestFilters.isEmpty()) {
    return lastAction;
  }
  RequestFilterAction action=requestFilters.get(0).filter(request);
  if (action instanceof ContinueAction) {
    Request newRequest=((ContinueAction)action).getRequest();
    return processFilters(newRequest,requestFilters.subList(1,requestFilters.size()),action);
  }
  return action;
}
