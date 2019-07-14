public StubImportBuilder stub(MappingBuilder stubMappingBuilder){
  mappings.add(stubMappingBuilder.build());
  return this;
}
