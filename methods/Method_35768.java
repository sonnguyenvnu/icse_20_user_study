@Override public void editStubMapping(StubMapping stubMapping){
  stubMappings.editMapping(stubMapping);
  if (stubMapping.shouldBePersisted()) {
    mappingsSaver.save(stubMapping);
  }
}
