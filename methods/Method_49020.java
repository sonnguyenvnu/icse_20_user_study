JanusGraphRelation findRelation(JanusGraphTransaction tx){
  JanusGraphVertex v=((StandardJanusGraphTx)tx).getInternalVertex(outVertexId);
  if (v == null || v.isRemoved())   return null;
  JanusGraphVertex typeVertex=tx.getVertex(typeId);
  if (typeVertex == null)   return null;
  if (!(typeVertex instanceof RelationType))   throw new IllegalArgumentException("Invalid RelationIdentifier: typeID does not reference a type");
  RelationType type=(RelationType)typeVertex;
  Iterable<? extends JanusGraphRelation> relations;
  if (((RelationType)typeVertex).isEdgeLabel()) {
    Direction dir=Direction.OUT;
    JanusGraphVertex other=((StandardJanusGraphTx)tx).getInternalVertex(inVertexId);
    if (other == null || other.isRemoved())     return null;
    if (((StandardJanusGraphTx)tx).isPartitionedVertex(v) && !((StandardJanusGraphTx)tx).isPartitionedVertex(other)) {
      JanusGraphVertex tmp=other;
      other=v;
      v=tmp;
      dir=Direction.IN;
    }
    relations=((VertexCentricQueryBuilder)v.query()).noPartitionRestriction().types((EdgeLabel)type).direction(dir).adjacent(other).edges();
  }
 else {
    relations=((VertexCentricQueryBuilder)v.query()).noPartitionRestriction().types((PropertyKey)type).properties();
  }
  for (  JanusGraphRelation r : relations) {
    if (r.longId() == relationId || ((r instanceof StandardRelation) && ((StandardRelation)r).getPreviousID() == relationId))     return r;
  }
  return null;
}
