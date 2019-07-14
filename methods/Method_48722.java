private long constructId(long count,long partition,VertexIDType type){
  Preconditions.checkArgument(partition < partitionIDBound && partition >= 0,"Invalid partition: %s",partition);
  Preconditions.checkArgument(count >= 0);
  Preconditions.checkArgument(VariableLong.unsignedBitLength(count) + partitionBits + (type == null ? 0 : type.offset()) <= TOTAL_BITS);
  Preconditions.checkArgument(type == null || type.isProper());
  long id=(count << partitionBits) + partition;
  if (type != null)   id=type.addPadding(id);
  return id;
}
