@Override protected int getBucketedSize(int requestSize){
  if (requestSize <= 0) {
    throw new InvalidSizeException(requestSize);
  }
  for (  int bucketedSize : mBucketSizes) {
    if (bucketedSize >= requestSize) {
      return bucketedSize;
    }
  }
  return requestSize;
}
