/** 
 * Like  {@link #memUTF8(CharSequence,boolean) memASCII}, but returns  {@code null} if {@code text} is {@code null}. 
 */
@Nullable public static ByteBuffer memUTF8Safe(@Nullable CharSequence text,boolean nullTerminated){
  return text == null ? null : memUTF8(text,nullTerminated);
}
