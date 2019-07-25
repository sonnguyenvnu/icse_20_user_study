/** 
 * Grow an array to a size that is larger than <code>minSize</code>, preserving content, and potentially reusing part of the provided array. 
 */
public LongArray grow(LongArray array,long minSize){
  if (minSize <= array.size()) {
    return array;
  }
  final long newSize=overSize(minSize,LONG_PAGE_SIZE,Long.BYTES);
  return resize(array,newSize);
}
