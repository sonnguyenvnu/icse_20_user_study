/** 
 * Like  {@link #memUTF16(CharSequence,boolean) memASCII}, but returns  {@code null} if {@code text} is {@code null}. 
 */
@Nullable public static ByteBuffer memUTF16Safe(@Nullable CharSequence text,boolean nullTerminated){
  return text == null ? null : memUTF16(text,nullTerminated);
}
