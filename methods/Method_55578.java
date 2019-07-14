/** 
 * Encodes the specified text on the stack using ASCII encoding and returns the encoded text length, in bytes. <p>Use  {@link #getPointerAddress} immediately after this method to get the encoded text address.</p>
 * @param text           the text to encode
 * @param nullTerminated if true, a null-terminator is included at the end of the encoded text
 */
public int nASCII(CharSequence text,boolean nullTerminated){
  return encodeASCII(text,nullTerminated,nmalloc(1,memLengthASCII(text,nullTerminated)));
}
