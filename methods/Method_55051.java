/** 
 * Array version of:  {@link #memcpy} 
 */
@NativeType("void *") public static long memcpy(@NativeType("void *") long[] dest,@NativeType("void const *") long[] src){
  if (CHECKS) {
    check(dest,src.length);
  }
  return nmemcpy(dest,src,Integer.toUnsignedLong(src.length) << 3);
}
