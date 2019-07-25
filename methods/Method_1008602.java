/** 
 * Ensure there are at least <code>maxBucketOrd</code> buckets available.
 */
public final void grow(long maxBucketOrd){
  docCounts=bigArrays.grow(docCounts,maxBucketOrd);
}
