private static boolean indexAppliesTo(IndexType index,JanusGraphElement element){
  return index.getElement().isInstance(element) && (!(index instanceof CompositeIndexType) || ((CompositeIndexType)index).getStatus() != SchemaStatus.DISABLED) && (!index.hasSchemaTypeConstraint() || index.getElement().matchesConstraint(index.getSchemaTypeConstraint(),element));
}
