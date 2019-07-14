public void enableRecordMappings(FileSource mappingsFileSource,FileSource filesFileSource){
  addMockServiceRequestListener(new StubMappingJsonRecorder(mappingsFileSource,filesFileSource,wireMockApp,options.matchingHeaders()));
  notifier.info("Recording mappings to " + mappingsFileSource.getPath());
}
