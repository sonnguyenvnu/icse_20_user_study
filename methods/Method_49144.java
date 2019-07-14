public static RelationCache readRelationCache(Entry data,StandardJanusGraphTx tx){
  return tx.getEdgeSerializer().readRelation(data,false,tx);
}
