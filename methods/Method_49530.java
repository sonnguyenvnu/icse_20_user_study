@Override public TypeInspector getTypeInspector(){
  for (  JanusGraphSchemaCategory sc : JanusGraphSchemaCategory.values()) {
    for (    JanusGraphVertex k : QueryUtil.getVertices(tx,BaseKey.SchemaCategory,sc)) {
      assert k instanceof JanusGraphSchemaVertex;
      JanusGraphSchemaVertex s=(JanusGraphSchemaVertex)k;
      if (sc.hasName()) {
        String name=s.name();
        Preconditions.checkNotNull(name);
      }
      TypeDefinitionMap dm=s.getDefinition();
      Preconditions.checkNotNull(dm);
      s.getRelated(TypeDefinitionCategory.TYPE_MODIFIER,Direction.OUT);
      s.getRelated(TypeDefinitionCategory.TYPE_MODIFIER,Direction.IN);
    }
  }
  return tx;
}
