/** 
 * Thread-local version of  {@link #UTF8(CharSequence,boolean)}. 
 */
@Nullable public static ByteBuffer stackUTF8Safe(@Nullable CharSequence text,boolean nullTerminated){
  return stackGet().UTF8Safe(text,nullTerminated);
}
