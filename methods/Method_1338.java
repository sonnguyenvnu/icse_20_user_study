/** 
 * A test-only method to get the underlying references. <p><b>DO NOT USE in application code.</b>
 */
@VisibleForTesting public synchronized SharedReference<T> getUnderlyingReferenceTestOnly(){
  return mSharedReference;
}
