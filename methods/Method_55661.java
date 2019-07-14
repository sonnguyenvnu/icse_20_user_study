/** 
 * Returns the number of bytes required to encode the specified text in the ASCII encoding.
 * @param value          the text to encode
 * @param nullTerminated if true, add the number of bytes required for null-termination
 * @return the number of bytes
 */
public static int memLengthASCII(CharSequence value,boolean nullTerminated){
  return value.length() + (nullTerminated ? 1 : 0);
}
