@Override public void setLocalPartitionBounds(List<PartitionIDRange> localPartitionIdRanges){
  Preconditions.checkArgument(localPartitionIdRanges != null && !localPartitionIdRanges.isEmpty());
  this.localPartitionIdRanges=Lists.newArrayList(localPartitionIdRanges);
  for (int i=0; i < currentPartitions.length; i++) {
    updateElement(i);
  }
}
