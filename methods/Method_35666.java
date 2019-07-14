public List<StubMapping> findAllStubsByMetadata(StringValuePattern pattern){
  return admin.findAllStubsByMetadata(pattern).getMappings();
}
