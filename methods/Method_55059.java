/** 
 * Array version of:  {@link #memmove} 
 */
@NativeType("void *") public static long memmove(@NativeType("void *") double[] dest,@NativeType("void const *") double[] src){
  if (CHECKS) {
    check(dest,src.length);
  }
  return nmemmove(dest,src,Integer.toUnsignedLong(src.length) << 3);
}
