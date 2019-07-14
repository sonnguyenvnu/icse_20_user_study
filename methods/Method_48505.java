private boolean isPartitionedAt(InternalRelation relation,int position){
  return idManager.isPartitionedVertex(relation.getVertex(position).longId());
}
