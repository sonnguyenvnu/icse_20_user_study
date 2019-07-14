@Override public void removeStubsByMetadata(StringValuePattern pattern){
  List<StubMapping> foundMappings=stubMappings.findByMetadata(pattern);
  for (  StubMapping mapping : foundMappings) {
    removeStubMapping(mapping);
  }
}
