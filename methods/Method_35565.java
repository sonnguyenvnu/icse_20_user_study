@Override public void addStubMapping(StubMapping stubMapping){
  if (stubMapping.getRequest().hasInlineCustomMatcher()) {
    throw new AdminException("Custom matchers can't be used when administering a remote WireMock server. " + "Use WireMockRule.stubFor() or WireMockServer.stubFor() to administer the local instance.");
  }
  executeRequest(adminRoutes.requestSpecForTask(CreateStubMappingTask.class),PathParams.empty(),stubMapping,Void.class);
}
