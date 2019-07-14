/** 
 * Computes number of bytes that can be safely read/written starting at given offset, but no more than count.
 */
static int adjustByteCount(final int offset,final int count,final int memorySize){
  final int available=Math.max(0,memorySize - offset);
  return Math.min(available,count);
}
