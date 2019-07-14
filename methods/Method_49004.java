@Override public byte getLifeCycle(){
  InternalVertex startVertex=getVertex(0);
  return ((startVertex.hasRemovedRelations() || startVertex.isRemoved()) && tx().isRemovedRelation(super.longId())) ? ElementLifeCycle.Removed : ElementLifeCycle.Loaded;
}
