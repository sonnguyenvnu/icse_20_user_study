public static Scenario inStartedState(String name){
  return new Scenario(UUID.randomUUID(),name,STARTED,ImmutableSet.of(STARTED),Collections.<StubMapping>emptySet());
}
