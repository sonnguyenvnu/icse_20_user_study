/** 
 * Array version of:  {@link #memset} 
 */
@NativeType("void *") public static long memset(@NativeType("void *") byte[] dest,int c){
  return nmemset(dest,c,Integer.toUnsignedLong(dest.length) << 0);
}
