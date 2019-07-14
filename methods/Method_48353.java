private StaticBuffer getLogKey(final int partitionId,final int bucketId,final int timeslice){
  Preconditions.checkArgument(partitionId >= 0 && partitionId < (1 << manager.partitionBitWidth));
  Preconditions.checkArgument(bucketId >= 0 && bucketId < numBuckets);
  DataOutput o=manager.serializer.getDataOutput(3 * 4);
  o.putInt((partitionId << (32 - manager.partitionBitWidth)));
  o.putInt(bucketId);
  o.putInt(timeslice);
  return o.getStaticBuffer();
}
