/** 
 * A test-only method to get the underlying references. <p><b>DO NOT USE in application code.</b>
 */
@VisibleForTesting public synchronized @Nullable SharedReference<PooledByteBuffer> getUnderlyingReferenceTestOnly(){
  return (mPooledByteBufferRef != null) ? mPooledByteBufferRef.getUnderlyingReferenceTestOnly() : null;
}
