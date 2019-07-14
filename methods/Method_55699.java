/** 
 * Converts the UTF-16 encoded string at the specified memory address to a  {@link String}.
 * @param address the string memory address
 * @param length  the number of characters to decode
 * @return the decoded {@link String}
 */
public static String memUTF16(long address,int length){
  if (length <= 0) {
    return "";
  }
  if (DEBUG) {
    int len=length << 1;
    byte[] bytes=len <= ARRAY_TLC_SIZE ? ARRAY_TLC_BYTE.get() : new byte[len];
    memByteBuffer(address,len).get(bytes,0,len);
    return new String(bytes,0,len,UTF16);
  }
  char[] chars=length <= ARRAY_TLC_SIZE ? ARRAY_TLC_CHAR.get() : new char[length];
  memCharBuffer(address,length).get(chars,0,length);
  return new String(chars,0,length);
}
