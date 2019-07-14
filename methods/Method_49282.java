public Iterable<JanusGraphEdge> getEdges(final TypeDefinitionCategory def,final Direction dir,JanusGraphSchemaVertex other){
  JanusGraphVertexQuery query=query().type(BaseLabel.SchemaDefinitionEdge).direction(dir);
  if (other != null)   query.adjacent(other);
  return Iterables.filter(query.edges(),(Predicate<JanusGraphEdge>)edge -> {
    final TypeDefinitionDescription desc=edge.valueOrNull(BaseKey.SchemaDefinitionDesc);
    return desc.getCategory() == def;
  }
);
}
