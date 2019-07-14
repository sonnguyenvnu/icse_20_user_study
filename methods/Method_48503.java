private void setLocalPartitionsToGlobal(int partitionBits){
  placementStrategy.setLocalPartitionBounds(PartitionIDRange.getGlobalRange(partitionBits));
}
