/** 
 * Returns the relative index for a given offset from the start of the queue.
 * @param offset The offset, which must be in the range [0, length].
 */
private int getRelativeIndex(int offset){
  int relativeIndex=relativeFirstIndex + offset;
  return relativeIndex < capacity ? relativeIndex : relativeIndex - capacity;
}
