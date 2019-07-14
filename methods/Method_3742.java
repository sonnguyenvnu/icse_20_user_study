/** 
 * Detaches the view at the provided index.
 * @param index Index of the child to return in regular perspective.
 */
void detachViewFromParent(int index){
  final int offset=getOffset(index);
  mBucket.remove(offset);
  mCallback.detachViewFromParent(offset);
  if (DEBUG) {
    Log.d(TAG,"detach view from parent " + index + ", off:" + offset);
  }
}
