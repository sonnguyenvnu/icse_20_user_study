/** 
 * Checks whether the run container contains x.
 * @param buf underlying ByteBuffer
 * @param position starting position of the container in the ByteBuffer
 * @param x target 16-bit value
 * @param numRuns number of runs
 * @return whether the run container contains x
 */
public static boolean contains(ByteBuffer buf,int position,short x,final int numRuns){
  int index=bufferedUnsignedInterleavedBinarySearch(buf,position,0,numRuns,x);
  if (index >= 0) {
    return true;
  }
  index=-index - 2;
  if (index != -1) {
    int offset=toIntUnsigned(x) - toIntUnsigned(buf.getShort(position + index * 2 * 2));
    int le=toIntUnsigned(buf.getShort(position + index * 2 * 2 + 2));
    if (offset <= le) {
      return true;
    }
  }
  return false;
}
