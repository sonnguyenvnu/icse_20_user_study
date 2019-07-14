public SnapshotRecordResult takeSnapshot(List<ServeEvent> serveEvents,RecordSpec recordSpec){
  final List<StubMapping> stubMappings=serveEventsToStubMappings(Lists.reverse(serveEvents),recordSpec.getFilters(),new SnapshotStubMappingGenerator(recordSpec.getCaptureHeaders(),recordSpec.getRequestBodyPatternFactory()),getStubMappingPostProcessor(admin.getOptions(),recordSpec));
  for (  StubMapping stubMapping : stubMappings) {
    if (recordSpec.shouldPersist()) {
      stubMapping.setPersistent(true);
    }
    admin.addStubMapping(stubMapping);
  }
  return recordSpec.getOutputFormat().format(stubMappings);
}
