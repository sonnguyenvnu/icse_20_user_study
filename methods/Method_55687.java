/** 
 * Converts the null-terminated ASCII encoded string at the specified memory address to a  {@link String}.
 * @param address the string memory address
 * @return the decoded {@link String}
 */
public static String memASCII(long address){
  return memASCII(address,memLengthNT1(address,Integer.MAX_VALUE));
}
