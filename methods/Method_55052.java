/** 
 * Array version of:  {@link #memcpy} 
 */
@NativeType("void *") public static long memcpy(@NativeType("void *") float[] dest,@NativeType("void const *") float[] src){
  if (CHECKS) {
    check(dest,src.length);
  }
  return nmemcpy(dest,src,Integer.toUnsignedLong(src.length) << 2);
}
