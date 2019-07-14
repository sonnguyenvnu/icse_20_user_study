/** 
 * Converts the null-terminated UTF-8 encoded string at the specified memory address to a  {@link String}.
 * @param address the string memory address
 * @return the decoded {@link String}
 */
public static String memUTF8(long address){
  return MultiReleaseTextDecoding.decodeUTF8(address,memLengthNT1(address,Integer.MAX_VALUE));
}
