/** 
 * Fills a memory area with a constant byte.
 * @param dest pointer to the memory area to fill
 * @param c    byte to set
 * @return the value of {@code dest}
 */
@NativeType("void *") public static long memset(@NativeType("void *") ShortBuffer dest,int c){
  return nmemset(memAddress(dest),c,Integer.toUnsignedLong(dest.remaining()) << 1);
}
