@Override public List<StubMapping> findStubMappingsByMetadata(StringValuePattern pattern){
  return client.findAllStubsByMetadata(pattern);
}
