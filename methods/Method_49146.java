private static InternalRelation readRelation(final InternalVertex vertex,final RelationCache relation,final Entry data,final TypeInspector types,final VertexFactory vertexFac){
  InternalRelationType type=TypeUtil.getBaseType((InternalRelationType)types.getExistingRelationType(relation.typeId));
  if (type.isPropertyKey()) {
    assert relation.direction == Direction.OUT;
    return new CacheVertexProperty(relation.relationId,(PropertyKey)type,vertex,relation.getValue(),data);
  }
  if (type.isEdgeLabel()) {
    InternalVertex otherVertex=vertexFac.getInternalVertex(relation.getOtherVertexId());
switch (relation.direction) {
case IN:
      return new CacheEdge(relation.relationId,(EdgeLabel)type,otherVertex,vertex,data);
case OUT:
    return new CacheEdge(relation.relationId,(EdgeLabel)type,vertex,otherVertex,data);
default :
  throw new AssertionError();
}
}
throw new AssertionError();
}
