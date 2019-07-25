/** 
 * Grow according to  {@link #growRatio},  {@link #minGrowCount} and{@link #maxGrowCount}.
 * @param currentBufferLength The current length of the buffer.
 * @param elementsCount The number of elements stored in the buffer.
 * @param expectedAdditions The number of expected additions to the buffer.
 * @return New buffer size.
 */
public int grow(int currentBufferLength,int elementsCount,int expectedAdditions){
  long growBy=(long)((long)currentBufferLength * growRatio);
  growBy=Math.max(growBy,minGrowCount);
  growBy=Math.min(growBy,maxGrowCount);
  long growTo=Math.min(MAX_ARRAY_LENGTH,growBy + currentBufferLength);
  long newSize=Math.max((long)elementsCount + expectedAdditions,growTo);
  if (newSize > MAX_ARRAY_LENGTH) {
    throw new BufferAllocationException("Java array size exceeded (current length: %d, elements: %d, expected additions: %d)",currentBufferLength,elementsCount,expectedAdditions);
  }
  return (int)newSize;
}
