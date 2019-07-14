/** 
 * Check that copy/read/write operation won't access memory it should not 
 */
static void checkBounds(final int offset,final int otherLength,final int otherOffset,final int count,final int memorySize){
  Preconditions.checkArgument(count >= 0);
  Preconditions.checkArgument(offset >= 0);
  Preconditions.checkArgument(otherOffset >= 0);
  Preconditions.checkArgument(offset + count <= memorySize);
  Preconditions.checkArgument(otherOffset + count <= otherLength);
}
