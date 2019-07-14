/** 
 * Array version of:  {@link #memset} 
 */
@NativeType("void *") public static long memset(@NativeType("void *") short[] dest,int c){
  return nmemset(dest,c,Integer.toUnsignedLong(dest.length) << 1);
}
