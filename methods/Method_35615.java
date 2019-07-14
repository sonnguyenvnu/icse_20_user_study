public StubMapping register(MappingBuilder mappingBuilder){
  StubMapping mapping=mappingBuilder.build();
  register(mapping);
  return mapping;
}
