/** 
 * Encodes the specified text on the stack using UTF8 encoding and returns a ByteBuffer that points to the encoded text.
 * @param text           the text to encode
 * @param nullTerminated if true, a null-terminator is included at the end of the encoded text
 */
public ByteBuffer UTF8(CharSequence text,boolean nullTerminated){
  int length=memLengthUTF8(text,nullTerminated);
  long target=nmalloc(1,length);
  encodeUTF8(text,nullTerminated,target);
  return MemoryUtil.wrap(BUFFER_BYTE,target,length).order(NATIVE_ORDER);
}
