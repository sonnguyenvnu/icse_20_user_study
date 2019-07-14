/** 
 * Converts the UTF-8 encoded string at the specified memory address to a  {@link String}.
 * @param address the string memory address
 * @param length  the number of bytes to decode
 * @return the decoded {@link String}
 */
public static String memUTF8(long address,int length){
  return MultiReleaseTextDecoding.decodeUTF8(address,length);
}
