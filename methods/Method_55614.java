/** 
 * Thread-local version of  {@link #ASCII(CharSequence,boolean)}. 
 */
@Nullable public static ByteBuffer stackASCIISafe(@Nullable CharSequence text,boolean nullTerminated){
  return stackGet().ASCIISafe(text,nullTerminated);
}
