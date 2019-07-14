private static void checkValidPartitionId(int partitionId,int partitionBitWidth){
  Preconditions.checkArgument(partitionId >= 0 && partitionId < (1 << partitionBitWidth));
}
