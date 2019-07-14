/** 
 * Frees the memory space pointed to by  {@code ptr}, which must have been returned by a previous call to  {@link #malloc},  {@link #calloc}, or  {@link #realloc}. Otherwise, or if  {@code free(ptr)} has already been called before, undefined behavior occurs. If ptr is {@code NULL}, no operation is performed.
 * @param ptr the memory space to free
 */
public static void free(@Nullable @NativeType("void *") ByteBuffer ptr){
  nfree(memAddressSafe(ptr));
}
