/** 
 * Array version of:  {@link #memmove} 
 */
@NativeType("void *") public static long memmove(@NativeType("void *") long[] dest,@NativeType("void const *") long[] src){
  if (CHECKS) {
    check(dest,src.length);
  }
  return nmemmove(dest,src,Integer.toUnsignedLong(src.length) << 3);
}
