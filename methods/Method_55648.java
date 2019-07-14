/** 
 * Sets all bytes in a specified block of memory to a copy of another block.
 * @param src the source memory address
 * @param dst the destination memory address
 */
public static void memCopy(IntBuffer src,IntBuffer dst){
  if (CHECKS) {
    check(dst,src.remaining());
  }
  MultiReleaseMemCopy.copy(memAddress(src),memAddress(dst),apiGetBytes(src.remaining(),2));
}
