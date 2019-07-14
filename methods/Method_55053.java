/** 
 * Array version of:  {@link #memcpy} 
 */
@NativeType("void *") public static long memcpy(@NativeType("void *") double[] dest,@NativeType("void const *") double[] src){
  if (CHECKS) {
    check(dest,src.length);
  }
  return nmemcpy(dest,src,Integer.toUnsignedLong(src.length) << 3);
}
