private synchronized InternalRelation update(){
  StandardEdge copy=new StandardEdge(super.longId(),edgeLabel(),getVertex(0),getVertex(1),ElementLifeCycle.Loaded);
  copyProperties(copy);
  copy.remove();
  StandardEdge u=(StandardEdge)tx().addEdge(getVertex(0),getVertex(1),edgeLabel());
  if (type.getConsistencyModifier() != ConsistencyModifier.FORK)   u.setId(super.longId());
  u.setPreviousID(super.longId());
  copyProperties(u);
  setId(u.longId());
  return u;
}
