/** 
 * Returns the index of the child in regular perspective.
 * @param child The child whose index will be returned.
 * @return The regular perspective index of the child or -1 if it does not exists.
 */
int indexOfChild(View child){
  final int index=mCallback.indexOfChild(child);
  if (index == -1) {
    return -1;
  }
  if (mBucket.get(index)) {
    if (DEBUG) {
      throw new IllegalArgumentException("cannot get index of a hidden child");
    }
 else {
      return -1;
    }
  }
  return index - mBucket.countOnesBefore(index);
}
