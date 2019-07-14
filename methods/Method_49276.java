@Override public Collection<Connection> mappedConnections(){
  String name=name();
  return StreamSupport.stream(getRelated(TypeDefinitionCategory.UPDATE_CONNECTION_EDGE,Direction.OUT).spliterator(),false).map(entry -> (JanusGraphSchemaVertex)entry.getSchemaType()).flatMap(s -> StreamSupport.stream(s.getEdges(TypeDefinitionCategory.CONNECTION_EDGE,Direction.OUT).spliterator(),false)).map(Connection::new).filter(s -> s.getEdgeLabel().equals(name)).collect(Collectors.toList());
}
