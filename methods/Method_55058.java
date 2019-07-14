/** 
 * Array version of:  {@link #memmove} 
 */
@NativeType("void *") public static long memmove(@NativeType("void *") float[] dest,@NativeType("void const *") float[] src){
  if (CHECKS) {
    check(dest,src.length);
  }
  return nmemmove(dest,src,Integer.toUnsignedLong(src.length) << 2);
}
