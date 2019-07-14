/** 
 * Like  {@link #memUTF8(long,int) memUTF8}, but returns  {@code null} if {@code address} is {@link #NULL}. 
 */
@Nullable public static String memUTF8Safe(long address,int length){
  return address == NULL ? null : MultiReleaseTextDecoding.decodeUTF8(address,length);
}
