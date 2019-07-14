/** 
 * Thread-local version of  {@link #UTF16(CharSequence)}. 
 */
@Nullable public static ByteBuffer stackUTF16Safe(@Nullable CharSequence text){
  return stackGet().UTF16Safe(text);
}
