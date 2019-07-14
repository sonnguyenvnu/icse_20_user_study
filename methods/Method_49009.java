@Override public byte getLifeCycle(){
  if ((getVertex(0).hasRemovedRelations() || getVertex(0).isRemoved()) && tx().isRemovedRelation(longId()))   return ElementLifeCycle.Removed;
 else   return ElementLifeCycle.Loaded;
}
