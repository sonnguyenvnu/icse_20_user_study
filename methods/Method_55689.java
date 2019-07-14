/** 
 * Like  {@link #memASCII(long) memASCII}, but returns  {@code null} if {@code address} is {@link #NULL}. 
 */
@Nullable public static String memASCIISafe(long address){
  return address == NULL ? null : memASCII(address,memLengthNT1(address,Integer.MAX_VALUE));
}
