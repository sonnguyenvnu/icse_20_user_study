/** 
 * Ensures  {@code peekBuffer} is large enough to store at least {@code length} bytes from thecurrent peek position.
 */
private void ensureSpaceForPeek(int length){
  int requiredLength=peekBufferPosition + length;
  if (requiredLength > peekBuffer.length) {
    int newPeekCapacity=Util.constrainValue(peekBuffer.length * 2,requiredLength + PEEK_MIN_FREE_SPACE_AFTER_RESIZE,requiredLength + PEEK_MAX_FREE_SPACE);
    peekBuffer=Arrays.copyOf(peekBuffer,newPeekCapacity);
  }
}
