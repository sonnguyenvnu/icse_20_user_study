private static Token getMinimumToken() throws PermanentBackendException {
  IPartitioner partitioner=StorageService.getPartitioner();
  if (partitioner instanceof RandomPartitioner) {
    return ((RandomPartitioner)partitioner).getMinimumToken();
  }
 else   if (partitioner instanceof Murmur3Partitioner) {
    return ((Murmur3Partitioner)partitioner).getMinimumToken();
  }
 else   if (partitioner instanceof ByteOrderedPartitioner) {
    return new ByteOrderedPartitioner.BytesToken(org.janusgraph.diskstorage.util.ByteBufferUtil.zeroByteBuffer(8));
  }
 else {
    throw new PermanentBackendException("Unsupported partitioner: " + partitioner);
  }
}
