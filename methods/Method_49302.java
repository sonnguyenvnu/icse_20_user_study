@Override public long getCompareId(){
  if (tx.isPartitionedVertex(this))   return tx.getIdInspector().getCanonicalVertexId(longId());
 else   return longId();
}
