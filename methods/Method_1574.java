/** 
 * Get default  {@link PoolParams}.
 */
public static PoolParams get(){
  SparseIntArray defaultBuckets=new SparseIntArray();
  defaultBuckets.put(DEFAULT_IO_BUFFER_SIZE,DEFAULT_BUCKET_SIZE);
  return new PoolParams(MAX_SIZE_SOFT_CAP,MAX_SIZE_HARD_CAP,defaultBuckets);
}
