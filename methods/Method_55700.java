/** 
 * Like  {@link #memUTF16(long) memUTF16}, but returns  {@code null} if {@code address} is {@link #NULL}. 
 */
@Nullable public static String memUTF16Safe(long address){
  return address == NULL ? null : memUTF16(address,memLengthNT2(address,Integer.MAX_VALUE - 1) >> 1);
}
