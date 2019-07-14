/** 
 * Returns a ByteBuffer containing the specified text UTF-16 encoded and optionally null-terminated.
 * @param text           the text to encode
 * @param nullTerminated if true, the text will be terminated with a '\0'.
 * @return the encoded text. The returned buffer must be deallocated manually with {@link #memFree}.
 */
public static ByteBuffer memUTF16(CharSequence text,boolean nullTerminated){
  int length=memLengthUTF16(text,nullTerminated);
  long target=nmemAlloc(length);
  encodeUTF16(text,nullTerminated,target);
  return wrap(BUFFER_BYTE,target,length).order(NATIVE_ORDER);
}
