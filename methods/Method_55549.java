/** 
 * Allocates a block of  {@code size} bytes of memory on the stack. The content of the newly allocated block of memory is not initialized, remaining withindeterminate values.
 * @param alignment the required alignment
 * @param size      the allocation size
 * @return the memory address on the stack for the requested allocation
 */
public long nmalloc(int alignment,int size){
  long address=(this.address + pointer - size) & ~Integer.toUnsignedLong(alignment - 1);
  pointer=(int)(address - this.address);
  if (CHECKS && pointer < 0) {
    throw new OutOfMemoryError("Out of stack space.");
  }
  return address;
}
