Scenario withoutStubMapping(StubMapping stubMapping){
  Set<StubMapping> newMappings=Sets.difference(stubMappings,ImmutableSet.of(stubMapping));
  return new Scenario(id,name,state,null,newMappings);
}
