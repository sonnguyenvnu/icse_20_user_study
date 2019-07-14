/** 
 * Get a cloned bitmap reference for the underlying original CloseableReference&lt;Bitmap&gt;. <p>After calling this method, this object can still be used. See  {@link #convertToBitmapReference()} for an alternative that detaches the original referenceinstead.
 * @return the cloned bitmap reference without altering this instance or null if already closed
 */
@Nullable public synchronized CloseableReference<Bitmap> cloneUnderlyingBitmapReference(){
  return CloseableReference.cloneOrNull(mBitmapReference);
}
