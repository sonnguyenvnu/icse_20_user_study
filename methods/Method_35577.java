@Override public void removeStubsByMetadata(StringValuePattern pattern){
  executeRequest(adminRoutes.requestSpecForTask(RemoveStubMappingsByMetadataTask.class),pattern,Void.class);
}
