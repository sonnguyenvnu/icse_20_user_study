@Override public ListStubMappingsResult findAllStubsByMetadata(StringValuePattern pattern){
  return executeRequest(adminRoutes.requestSpecForTask(FindStubMappingsByMetadataTask.class),pattern,ListStubMappingsResult.class);
}
