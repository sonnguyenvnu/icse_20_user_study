/** 
 * Like  {@link #memUTF8(long) memUTF8}, but returns  {@code null} if {@code address} is {@link #NULL}. 
 */
@Nullable public static String memUTF8Safe(long address){
  return address == NULL ? null : MultiReleaseTextDecoding.decodeUTF8(address,memLengthNT1(address,Integer.MAX_VALUE));
}
