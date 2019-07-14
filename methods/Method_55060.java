/** 
 * Fills memory with a constant byte.
 * @param dest pointer to destination
 * @param c    character to set
 * @return the value of {@code dest}
 */
@NativeType("void *") public static <T extends CustomBuffer<T>>long memset(@NativeType("void *") T dest,@NativeType("int") int c){
  return nmemset(memAddress(dest),c,Integer.toUnsignedLong(dest.remaining()) * dest.sizeof());
}
