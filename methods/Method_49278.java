@Override public String name(){
  if (name == null) {
    JanusGraphVertexProperty<String> p;
    if (isLoaded()) {
      StandardJanusGraphTx tx=tx();
      p=(JanusGraphVertexProperty)Iterables.getOnlyElement(RelationConstructor.readRelation(this,tx.getGraph().getSchemaCache().getSchemaRelations(longId(),BaseKey.SchemaName,Direction.OUT),tx),null);
    }
 else {
      p=Iterables.getOnlyElement(query().type(BaseKey.SchemaName).properties(),null);
    }
    Preconditions.checkNotNull(p,"Could not find type for id: %s",longId());
    name=p.value();
  }
  assert name != null;
  return JanusGraphSchemaCategory.getName(name);
}
