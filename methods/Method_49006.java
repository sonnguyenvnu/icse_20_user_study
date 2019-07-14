private synchronized InternalRelation update(){
  StandardVertexProperty copy=new StandardVertexProperty(longId(),propertyKey(),getVertex(0),value(),ElementLifeCycle.Loaded);
  copyProperties(copy);
  copy.remove();
  StandardVertexProperty u=(StandardVertexProperty)tx().addProperty(getVertex(0),propertyKey(),value());
  if (type.getConsistencyModifier() != ConsistencyModifier.FORK)   u.setId(longId());
  u.setPreviousID(longId());
  copyProperties(u);
  return u;
}
