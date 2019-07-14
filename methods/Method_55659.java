/** 
 * Encodes and optionally null-terminates the specified text using ASCII encoding. The encoded text is stored in the specified  {@link ByteBuffer} at thespecified  {@code position} offset. The current buffer position is not modified by this operation. The {@code target} buffer is assumed to have enoughremaining space to store the encoded text.
 * @param text           the text to encode
 * @param nullTerminated if true, the text will be terminated with a '\0'.
 * @param offset         the buffer position to which the string will be encoded
 * @return the number of bytes of the encoded string
 */
public static int memASCII(CharSequence text,boolean nullTerminated,ByteBuffer target,int offset){
  return encodeASCII(text,nullTerminated,memAddress(target,offset));
}
