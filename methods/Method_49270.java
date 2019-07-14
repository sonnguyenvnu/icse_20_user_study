@Override public Collection<PropertyKey> mappedProperties(){
  return StreamSupport.stream(getRelated(TypeDefinitionCategory.PROPERTY_KEY_EDGE,Direction.OUT).spliterator(),false).map(entry -> (PropertyKey)entry.getSchemaType()).collect(Collectors.toList());
}
