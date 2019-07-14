/** 
 * Returns whether this instance is closed.
 */
@Override public synchronized boolean isClosed(){
  return mBitmapReference == null;
}
