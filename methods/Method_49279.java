@Override public TypeDefinitionMap getDefinition(){
  TypeDefinitionMap def=definition;
  if (def == null) {
    def=new TypeDefinitionMap();
    Iterable<JanusGraphVertexProperty> ps;
    if (isLoaded()) {
      StandardJanusGraphTx tx=tx();
      ps=(Iterable)RelationConstructor.readRelation(this,tx.getGraph().getSchemaCache().getSchemaRelations(longId(),BaseKey.SchemaDefinitionProperty,Direction.OUT),tx);
    }
 else {
      ps=query().type(BaseKey.SchemaDefinitionProperty).properties();
    }
    for (    JanusGraphVertexProperty property : ps) {
      TypeDefinitionDescription desc=property.valueOrNull(BaseKey.SchemaDefinitionDesc);
      Preconditions.checkArgument(desc != null && desc.getCategory().isProperty());
      def.setValue(desc.getCategory(),property.value());
    }
    assert def.size() > 0;
    definition=def;
  }
  assert def != null;
  return def;
}
