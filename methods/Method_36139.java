@Override public void save(List<StubMapping> stubMappings){
  for (  StubMapping mapping : stubMappings) {
    if (mapping != null && mapping.isDirty()) {
      save(mapping);
    }
  }
}
