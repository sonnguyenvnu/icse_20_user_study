@Override public Iterable<Entry> getRelated(TypeDefinitionCategory def,Direction dir){
  assert dir == Direction.OUT || dir == Direction.IN;
  ListMultimap<TypeDefinitionCategory,Entry> relations=dir == Direction.OUT ? outRelations : inRelations;
  if (relations == null) {
    ImmutableListMultimap.Builder<TypeDefinitionCategory,Entry> b=ImmutableListMultimap.builder();
    Iterable<JanusGraphEdge> edges;
    if (isLoaded()) {
      StandardJanusGraphTx tx=tx();
      edges=(Iterable)RelationConstructor.readRelation(this,tx.getGraph().getSchemaCache().getSchemaRelations(longId(),BaseLabel.SchemaDefinitionEdge,dir),tx);
    }
 else {
      edges=query().type(BaseLabel.SchemaDefinitionEdge).direction(dir).edges();
    }
    for (    JanusGraphEdge edge : edges) {
      JanusGraphVertex oth=edge.vertex(dir.opposite());
      assert oth instanceof JanusGraphSchemaVertex;
      TypeDefinitionDescription desc=edge.valueOrNull(BaseKey.SchemaDefinitionDesc);
      Object modifier=null;
      if (desc.getCategory().hasDataType()) {
        assert desc.getModifier() != null && desc.getModifier().getClass().equals(desc.getCategory().getDataType());
        modifier=desc.getModifier();
      }
      b.put(desc.getCategory(),new Entry((JanusGraphSchemaVertex)oth,modifier));
    }
    relations=b.build();
    if (dir == Direction.OUT)     outRelations=relations;
 else     inRelations=relations;
  }
  assert relations != null;
  return relations.get(def);
}
