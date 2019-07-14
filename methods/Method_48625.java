private void setTypeModifier(final JanusGraphSchemaElement element,final ModifierType modifierType,final Object value){
  Preconditions.checkArgument(element != null,"null schema element");
  TypeDefinitionCategory cat=modifierType.getCategory();
  if (cat.hasDataType() && null != value) {
    Preconditions.checkArgument(cat.getDataType().equals(value.getClass()),"modifier value is not of expected type " + cat.getDataType());
  }
  JanusGraphSchemaVertex typeVertex;
  if (element instanceof JanusGraphSchemaVertex) {
    typeVertex=(JanusGraphSchemaVertex)element;
  }
 else   if (element instanceof JanusGraphIndex) {
    IndexType index=((JanusGraphIndexWrapper)element).getBaseIndex();
    assert index instanceof IndexTypeWrapper;
    SchemaSource base=((IndexTypeWrapper)index).getSchemaBase();
    typeVertex=(JanusGraphSchemaVertex)base;
  }
 else   throw new IllegalArgumentException("Invalid schema element: " + element);
  for (  JanusGraphEdge e : typeVertex.getEdges(TypeDefinitionCategory.TYPE_MODIFIER,Direction.OUT)) {
    JanusGraphSchemaVertex v=(JanusGraphSchemaVertex)e.vertex(Direction.IN);
    TypeDefinitionMap def=v.getDefinition();
    Object existingValue=def.getValue(modifierType.getCategory());
    if (null != existingValue) {
      if (existingValue.equals(value)) {
        return;
      }
 else {
        e.remove();
        v.remove();
      }
    }
  }
  if (null != value) {
    TypeDefinitionMap def=new TypeDefinitionMap();
    def.setValue(cat,value);
    JanusGraphSchemaVertex cVertex=transaction.makeSchemaVertex(JanusGraphSchemaCategory.TYPE_MODIFIER,null,def);
    addSchemaEdge(typeVertex,cVertex,TypeDefinitionCategory.TYPE_MODIFIER,null);
  }
  updateSchemaVertex(typeVertex);
  updatedTypes.add(typeVertex);
}
