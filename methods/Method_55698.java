/** 
 * Converts the null-terminated UTF-16 encoded string at the specified memory address to a  {@link String}.
 * @param address the string memory address
 * @return the decoded {@link String}
 */
public static String memUTF16(long address){
  return memUTF16(address,memLengthNT2(address,Integer.MAX_VALUE - 1) >> 1);
}
