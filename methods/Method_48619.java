private void setStatusEdges(JanusGraphSchemaVertex vertex,SchemaStatus status,Set<PropertyKeyVertex> keys){
  Preconditions.checkArgument(vertex.asIndexType().isMixedIndex());
  for (  JanusGraphEdge edge : vertex.getEdges(TypeDefinitionCategory.INDEX_FIELD,Direction.OUT)) {
    if (!keys.contains(edge.vertex(Direction.IN)))     continue;
    TypeDefinitionDescription desc=edge.valueOrNull(BaseKey.SchemaDefinitionDesc);
    assert desc.getCategory() == TypeDefinitionCategory.INDEX_FIELD;
    Parameter[] parameters=(Parameter[])desc.getModifier();
    assert parameters[parameters.length - 1].key().equals(ParameterType.STATUS.getName());
    if (parameters[parameters.length - 1].value().equals(status))     continue;
    Parameter[] paraCopy=Arrays.copyOf(parameters,parameters.length);
    paraCopy[parameters.length - 1]=ParameterType.STATUS.getParameter(status);
    edge.remove();
    addSchemaEdge(vertex,edge.vertex(Direction.IN),TypeDefinitionCategory.INDEX_FIELD,paraCopy);
  }
  for (  PropertyKeyVertex prop : keys)   prop.resetCache();
}
