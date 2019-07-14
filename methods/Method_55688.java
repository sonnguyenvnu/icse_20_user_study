/** 
 * Converts the ASCII encoded string at the specified memory address to a  {@link String}.
 * @param address the string memory address
 * @param length  the number of bytes to decode
 * @return the decoded {@link String}
 */
@SuppressWarnings("deprecation") public static String memASCII(long address,int length){
  if (length <= 0) {
    return "";
  }
  byte[] ascii=length <= ARRAY_TLC_SIZE ? ARRAY_TLC_BYTE.get() : new byte[length];
  memByteBuffer(address,length).get(ascii,0,length);
  return new String(ascii,0,0,length);
}
