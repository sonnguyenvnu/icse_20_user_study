public static List<PartitionIDRange> getGlobalRange(final int partitionBits){
  Preconditions.checkArgument(partitionBits >= 0 && partitionBits < (Integer.SIZE - 1),"Invalid partition bits: %s",partitionBits);
  final int partitionIdBound=(1 << (partitionBits));
  return ImmutableList.of(new PartitionIDRange(0,partitionIdBound,partitionIdBound));
}
