Scenario withStubMapping(StubMapping stubMapping){
  Set<StubMapping> newMappings=ImmutableSet.<StubMapping>builder().addAll(stubMappings).add(stubMapping).build();
  return new Scenario(id,name,state,null,newMappings);
}
