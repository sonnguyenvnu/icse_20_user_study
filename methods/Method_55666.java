/** 
 * Returns the number of bytes required to encode the specified text in the UTF-8 encoding.
 * @param value          the text to encode
 * @param nullTerminated if true, add the number of bytes required for null-termination
 * @return the number of bytes
 */
public static int memLengthUTF8(CharSequence value,boolean nullTerminated){
  int i, len=value.length(), bytes=len;
  for (i=0; i < len; i++) {
    if (0x80 <= value.charAt(i)) {
      break;
    }
  }
  for (; i < len; i++) {
    char c=value.charAt(i);
    if (0x800 <= c) {
      bytes+=encodeUTF8LengthSlow(value,i,len);
      break;
    }
    bytes+=(0x7F - c) >>> 31;
  }
  return bytes + (nullTerminated ? 1 : 0);
}
