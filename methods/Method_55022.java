/** 
 * Changes the size of the memory block pointed to by  {@code ptr} to {@code size} bytes  The contents will be unchanged in the range from the start of theregion up to the minimum of the old and new sizes. If the new size is larger than the old size, the added memory will not be initialized. If {@code ptr} is {@code NULL}, then the call is equivalent to  {@code malloc(size)}, for all values of  {@code size}; if  {@code size} is equal to zero, and{@code ptr} is not {@code NULL}, then the call is equivalent to  {@code free(ptr)}. Unless  {@code ptr} is {@code NULL}, it must have been returned by an earlier call to  {@link #malloc},  {@link #calloc} or {@link #realloc}. If the area pointed to was moved, a  {@code free(ptr)} is done.
 * @param ptr  the memory block to reallocate
 * @param size the new memory block size, in bytes
 */
@Nullable @NativeType("void *") public static ByteBuffer realloc(@Nullable @NativeType("void *") ByteBuffer ptr,@NativeType("size_t") long size){
  long __result=nrealloc(memAddressSafe(ptr),size);
  return memByteBufferSafe(__result,(int)size);
}
