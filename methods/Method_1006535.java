@NonNull @Override public RouteResponse intercept(Chain chain){
  List<AbsExplicitMatcher> matcherList=MatcherRegistry.getExplicitMatcher();
  if (matcherList.isEmpty()) {
    return RouteResponse.assemble(RouteStatus.FAILED,"The MatcherRegistry contains no explicit matcher.");
  }
  if (AptHub.routeTable.isEmpty()) {
    return RouteResponse.assemble(RouteStatus.FAILED,"The RouteTable is empty.");
  }
  return chain.process();
}
