/** 
 * Like  {@link #memASCII(long,int) memASCII}, but returns  {@code null} if {@code address} is {@link #NULL}. 
 */
@Nullable public static String memASCIISafe(long address,int length){
  return address == NULL ? null : memASCII(address,length);
}
