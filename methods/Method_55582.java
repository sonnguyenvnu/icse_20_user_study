/** 
 * Encodes the specified text on the stack using UTF16 encoding and returns the encoded text length, in bytes. <p>Use  {@link #getPointerAddress} immediately after this method to get the encoded text address.</p>
 * @param text           the text to encode
 * @param nullTerminated if true, a null-terminator is included at the end of the encoded text
 */
public int nUTF16(CharSequence text,boolean nullTerminated){
  return encodeUTF16(text,nullTerminated,nmalloc(2,memLengthUTF16(text,nullTerminated)));
}
