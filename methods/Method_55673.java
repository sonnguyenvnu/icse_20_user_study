/** 
 * Encodes and optionally null-terminates the specified text using UTF-16 encoding. The encoded text is stored in the specified  {@link ByteBuffer} at thespecified  {@code position} offset. The current buffer position is not modified by this operation. The {@code target} buffer is assumed to have enoughremaining space to store the encoded text.
 * @param text           the text to encode
 * @param nullTerminated if true, the text will be terminated with a '\0'.
 * @param target         the buffer in which to store the encoded text
 * @param offset         the buffer position to which the string will be encoded
 * @return the number of bytes of the encoded string
 */
public static int memUTF16(CharSequence text,boolean nullTerminated,ByteBuffer target,int offset){
  return encodeUTF16(text,nullTerminated,memAddress(target,offset));
}
