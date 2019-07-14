@Override public ServeEvent serveFor(Request request){
  StubMapping matchingMapping=find(mappings,mappingMatchingAndInCorrectScenarioState(request),StubMapping.NOT_CONFIGURED);
  scenarios.onStubServed(matchingMapping);
  ResponseDefinition responseDefinition=applyTransformations(request,matchingMapping.getResponse(),ImmutableList.copyOf(transformers.values()));
  return ServeEvent.of(LoggedRequest.createFrom(request),copyOf(responseDefinition),matchingMapping);
}
