public void putRepeatedRequestsInScenarios(List<StubMapping> stubMappings){
  ImmutableListMultimap<RequestPattern,StubMapping> stubsGroupedByRequest=Multimaps.index(stubMappings,new Function<StubMapping,RequestPattern>(){
    @Override public RequestPattern apply(    StubMapping mapping){
      return mapping.getRequest();
    }
  }
);
  Map<RequestPattern,Collection<StubMapping>> groupsWithMoreThanOneStub=Maps.filterEntries(stubsGroupedByRequest.asMap(),new Predicate<Map.Entry<RequestPattern,Collection<StubMapping>>>(){
    @Override public boolean apply(    Map.Entry<RequestPattern,Collection<StubMapping>> input){
      return input.getValue().size() > 1;
    }
  }
);
  for (  Map.Entry<RequestPattern,Collection<StubMapping>> entry : groupsWithMoreThanOneStub.entrySet()) {
    putStubsInScenario(ImmutableList.copyOf(entry.getValue()));
  }
}
