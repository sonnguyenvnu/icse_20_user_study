@Override public void removeStubMapping(StubMapping stubMapping){
  stubMappings.removeMapping(stubMapping);
  if (stubMapping.shouldBePersisted()) {
    mappingsSaver.remove(stubMapping);
  }
}
