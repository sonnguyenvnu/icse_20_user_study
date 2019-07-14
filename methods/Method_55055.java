/** 
 * Array version of:  {@link #memmove} 
 */
@NativeType("void *") public static long memmove(@NativeType("void *") short[] dest,@NativeType("void const *") short[] src){
  if (CHECKS) {
    check(dest,src.length);
  }
  return nmemmove(dest,src,Integer.toUnsignedLong(src.length) << 1);
}
