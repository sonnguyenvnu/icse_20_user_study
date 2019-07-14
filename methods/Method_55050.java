/** 
 * Array version of:  {@link #memcpy} 
 */
@NativeType("void *") public static long memcpy(@NativeType("void *") int[] dest,@NativeType("void const *") int[] src){
  if (CHECKS) {
    check(dest,src.length);
  }
  return nmemcpy(dest,src,Integer.toUnsignedLong(src.length) << 2);
}
