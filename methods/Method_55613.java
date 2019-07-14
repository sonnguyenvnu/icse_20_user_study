/** 
 * Thread-local version of  {@link #ASCII(CharSequence)}. 
 */
@Nullable public static ByteBuffer stackASCIISafe(@Nullable CharSequence text){
  return stackGet().ASCIISafe(text);
}
