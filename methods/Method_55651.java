/** 
 * Sets all bytes in a specified block of memory to a copy of another block.
 * @param src   the source memory address
 * @param dst   the destination memory address
 * @param bytes the number of bytes to copy
 */
public static void memCopy(long src,long dst,long bytes){
  if (DEBUG && (src == NULL || dst == NULL || bytes < 0)) {
    throw new IllegalArgumentException();
  }
  MultiReleaseMemCopy.copy(src,dst,bytes);
}
