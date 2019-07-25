/** 
 * Allocate a number of blocks and mark them as used.
 * @param length the number of bytes to allocate
 * @return the start position in bytes
 */
public long allocate(int length){
  return freeSpace.allocate(length);
}
