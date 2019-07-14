@Override public Collection<Connection> mappedConnections(){
  return StreamSupport.stream(getRelated(TypeDefinitionCategory.CONNECTION_EDGE,Direction.OUT).spliterator(),false).map(entry -> new Connection((String)entry.getModifier(),this,(VertexLabel)entry.getSchemaType())).collect(Collectors.toList());
}
