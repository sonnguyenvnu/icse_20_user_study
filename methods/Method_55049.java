/** 
 * Array version of:  {@link #memcpy} 
 */
@NativeType("void *") public static long memcpy(@NativeType("void *") short[] dest,@NativeType("void const *") short[] src){
  if (CHECKS) {
    check(dest,src.length);
  }
  return nmemcpy(dest,src,Integer.toUnsignedLong(src.length) << 1);
}
