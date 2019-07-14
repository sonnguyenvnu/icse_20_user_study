/** 
 * Encodes and optionally null-terminates the specified text using UTF-8 encoding. The encoded text is stored in the specified  {@link ByteBuffer}, at the current buffer position. The current buffer position is not modified by this operation. The  {@code target} buffer is assumed to have enough remainingspace to store the encoded text. The specified text is assumed to be a valid UTF-16 string.
 * @param text           the text to encode
 * @param nullTerminated if true, the text will be terminated with a '\0'.
 * @param target         the buffer in which to store the encoded text
 * @return the number of bytes of the encoded string
 */
public static int memUTF8(CharSequence text,boolean nullTerminated,ByteBuffer target){
  return encodeUTF8(text,nullTerminated,memAddress(target));
}
