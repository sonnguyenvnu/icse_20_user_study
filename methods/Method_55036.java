/** 
 * Copies bytes between memory areas that must not overlap.
 * @param dest pointer to the destination memory area
 * @param src  pointer to the source memory area
 * @return the value of {@code dest}
 */
@NativeType("void *") public static long memcpy(@NativeType("void *") LongBuffer dest,@NativeType("void const *") LongBuffer src){
  if (CHECKS) {
    check(dest,src.remaining());
  }
  return nmemcpy(memAddress(dest),memAddress(src),Integer.toUnsignedLong(src.remaining()) << 3);
}
