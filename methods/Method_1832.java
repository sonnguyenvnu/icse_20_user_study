/** 
 * Convert this object to a CloseableReference&lt;Bitmap&gt;. <p>You cannot call this method on an object that has already been closed. <p>The reference count of the bitmap is preserved. After calling this method, this object can no longer be used and no longer points to the bitmap. <p>See  {@link #cloneUnderlyingBitmapReference()} for an alternative that returns a clonedbitmap reference instead.
 * @return the underlying bitmap reference after being detached from this instance
 * @throws IllegalArgumentException if this object has already been closed.
 */
public synchronized CloseableReference<Bitmap> convertToBitmapReference(){
  Preconditions.checkNotNull(mBitmapReference,"Cannot convert a closed static bitmap");
  return detachBitmapReference();
}
