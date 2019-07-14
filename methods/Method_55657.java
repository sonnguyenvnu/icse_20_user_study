/** 
 * Like  {@link #memASCII(CharSequence,boolean) memASCII}, but returns  {@code null} if {@code text} is {@code null}. 
 */
@Nullable public static ByteBuffer memASCIISafe(@Nullable CharSequence text,boolean nullTerminated){
  return text == null ? null : memASCII(text,nullTerminated);
}
