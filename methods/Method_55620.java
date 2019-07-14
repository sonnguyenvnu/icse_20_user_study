/** 
 * Unsafe version of  {@link #memAlloc}. May return  {@link #NULL} if {@code size} is zero or the allocation failed. 
 */
public static long nmemAlloc(long size){
  return ALLOCATOR.malloc(size);
}
