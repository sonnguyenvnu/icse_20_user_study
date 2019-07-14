public static InternalRelation parseRelation(TransactionLogHeader.Modification modification,StandardJanusGraphTx tx){
  Change state=modification.state;
  assert state.isProper();
  long outVertexId=modification.outVertexId;
  Entry relEntry=modification.relationEntry;
  InternalVertex outVertex=tx.getInternalVertex(outVertexId);
  RelationCache relCache=tx.getEdgeSerializer().readRelation(relEntry,false,tx);
  assert relCache.direction == Direction.OUT;
  InternalRelationType type=(InternalRelationType)tx.getExistingRelationType(relCache.typeId);
  assert type.getBaseType() == null;
  InternalRelation rel;
  if (type.isPropertyKey()) {
    if (state == Change.REMOVED) {
      rel=new StandardVertexProperty(relCache.relationId,(PropertyKey)type,outVertex,relCache.getValue(),ElementLifeCycle.Removed);
    }
 else {
      rel=new CacheVertexProperty(relCache.relationId,(PropertyKey)type,outVertex,relCache.getValue(),relEntry);
    }
  }
 else {
    assert type.isEdgeLabel();
    InternalVertex otherVertex=tx.getInternalVertex(relCache.getOtherVertexId());
    if (state == Change.REMOVED) {
      rel=new StandardEdge(relCache.relationId,(EdgeLabel)type,outVertex,otherVertex,ElementLifeCycle.Removed);
    }
 else {
      rel=new CacheEdge(relCache.relationId,(EdgeLabel)type,outVertex,otherVertex,relEntry);
    }
  }
  if (state == Change.REMOVED && relCache.hasProperties()) {
    for (    LongObjectCursor<Object> entry : relCache) {
      rel.setPropertyDirect(tx.getExistingPropertyKey(entry.key),entry.value);
    }
  }
  return rel;
}
