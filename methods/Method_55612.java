/** 
 * Thread-local version of  {@link #UTF16(CharSequence)}. 
 */
public static ByteBuffer stackUTF16(CharSequence text){
  return stackGet().UTF16(text);
}
