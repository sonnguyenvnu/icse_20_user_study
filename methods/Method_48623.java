public JanusGraphSchemaVertex getSchemaVertex(JanusGraphSchemaElement element){
  if (element instanceof RelationType) {
    Preconditions.checkArgument(element instanceof RelationTypeVertex,"Invalid schema element provided: %s",element);
    return (RelationTypeVertex)element;
  }
 else   if (element instanceof RelationTypeIndex) {
    return (RelationTypeVertex)((RelationTypeIndexWrapper)element).getWrappedType();
  }
 else   if (element instanceof VertexLabel) {
    Preconditions.checkArgument(element instanceof VertexLabelVertex,"Invalid schema element provided: %s",element);
    return (VertexLabelVertex)element;
  }
 else   if (element instanceof JanusGraphIndex) {
    Preconditions.checkArgument(element instanceof JanusGraphIndexWrapper,"Invalid schema element provided: %s",element);
    IndexType index=((JanusGraphIndexWrapper)element).getBaseIndex();
    assert index instanceof IndexTypeWrapper;
    SchemaSource base=((IndexTypeWrapper)index).getSchemaBase();
    assert base instanceof JanusGraphSchemaVertex;
    return (JanusGraphSchemaVertex)base;
  }
  throw new IllegalArgumentException("Invalid schema element provided: " + element);
}
