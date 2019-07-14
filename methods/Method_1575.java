public static SparseIntArray generateBuckets(int min,int max,int numThreads){
  SparseIntArray buckets=new SparseIntArray();
  for (int i=min; i <= max; i*=2) {
    buckets.put(i,numThreads);
  }
  return buckets;
}
