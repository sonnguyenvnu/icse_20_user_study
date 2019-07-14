/** 
 * Copies  {@code count} bytes from memory area {@code src} to memory area {@code dest}. <p>The memory areas may overlap: copying takes place as though the bytes in  {@code src} are first copied into a temporary array that does not overlap{@code src} or {@code dest}, and the bytes are then copied from the temporary array to  {@code dest}.</p>
 * @param dest pointer to the destination memory area
 * @param src  pointer to the source memory area
 * @return the value of {@code dest}
 */
@NativeType("void *") public static long memmove(@NativeType("void *") ByteBuffer dest,@NativeType("void const *") ByteBuffer src){
  if (CHECKS) {
    check(dest,src.remaining());
  }
  return nmemmove(memAddress(dest),memAddress(src),src.remaining());
}
