private Request applyFilterSpecs(Request request){
  try {
    for (    String filterSpec : filterSpecs) {
      Filter filter=FilterFactories.createFilterFromFilterSpec(request,filterSpec);
      request=request.filterWith(filter);
    }
    return request;
  }
 catch (  FilterNotCreatedException e) {
    return errorReport(e);
  }
}
