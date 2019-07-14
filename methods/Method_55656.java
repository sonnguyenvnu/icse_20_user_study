/** 
 * Returns a ByteBuffer containing the specified text ASCII encoded and optionally null-terminated.
 * @param text           the text to encode
 * @param nullTerminated if true, the text will be terminated with a '\0'.
 * @return the encoded text. The returned buffer must be deallocated manually with {@link #memFree}.
 */
public static ByteBuffer memASCII(CharSequence text,boolean nullTerminated){
  int length=memLengthASCII(text,nullTerminated);
  long target=nmemAlloc(length);
  encodeASCII(text,nullTerminated,target);
  return wrap(BUFFER_BYTE,target,length).order(NATIVE_ORDER);
}
