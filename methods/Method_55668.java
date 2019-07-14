/** 
 * Returns a ByteBuffer containing the specified text UTF-16 encoded and null-terminated.
 * @param text the text to encode
 * @return the encoded text. The returned buffer must be deallocated manually with {@link #memFree}.
 */
public static ByteBuffer memUTF16(CharSequence text){
  return memUTF16(text,true);
}
