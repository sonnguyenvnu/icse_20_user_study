public List<StubMapping> process(Iterable<StubMapping> stubMappings){
  final Multiset<RequestPattern> requestCounts=HashMultiset.create();
  final List<StubMapping> processedStubMappings=new ArrayList<>();
  for (  StubMapping stubMapping : stubMappings) {
    requestCounts.add(stubMapping.getRequest());
    if (requestCounts.count(stubMapping.getRequest()) > 1 && !shouldRecordRepeatsAsScenarios) {
      continue;
    }
    if (bodyExtractMatcher != null && bodyExtractMatcher.match(stubMapping.getResponse()).isExactMatch()) {
      bodyExtractor.extractInPlace(stubMapping);
    }
    processedStubMappings.add(stubMapping);
  }
  if (shouldRecordRepeatsAsScenarios) {
    new ScenarioProcessor().putRepeatedRequestsInScenarios(processedStubMappings);
  }
  return Lists.transform(processedStubMappings,transformerRunner);
}
