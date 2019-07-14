/** 
 * Thread-local version of  {@link #UTF16(CharSequence,boolean)}. 
 */
@Nullable public static ByteBuffer stackUTF16Safe(@Nullable CharSequence text,boolean nullTerminated){
  return stackGet().UTF16Safe(text,nullTerminated);
}
