/** 
 * Array version of:  {@link #memmove} 
 */
@NativeType("void *") public static long memmove(@NativeType("void *") byte[] dest,@NativeType("void const *") byte[] src){
  if (CHECKS) {
    check(dest,src.length);
  }
  return nmemmove(dest,src,Integer.toUnsignedLong(src.length) << 0);
}
