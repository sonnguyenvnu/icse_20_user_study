/** 
 * Thread-local version of  {@link #UTF8(CharSequence)}. 
 */
@Nullable public static ByteBuffer stackUTF8Safe(@Nullable CharSequence text){
  return stackGet().UTF8Safe(text);
}
