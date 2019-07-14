/** 
 * Unsafe version of  {@link #memAlignedAlloc} that checks the returned pointer.
 * @return a pointer to the memory block allocated by the function on success. This pointer will never be {@link #NULL}, even if  {@code size} is zero.
 * @throws OutOfMemoryError if the function failed to allocate the requested block of memory
 */
public static long nmemAlignedAllocChecked(long alignment,long size){
  long address=nmemAlignedAlloc(alignment,size != 0 ? size : 1L);
  if (CHECKS && address == NULL) {
    throw new OutOfMemoryError();
  }
  return address;
}
