/** 
 * Like  {@link #memUTF16(CharSequence) memASCII}, but returns  {@code null} if {@code text} is {@code null}. 
 */
@Nullable public static ByteBuffer memUTF16Safe(@Nullable CharSequence text){
  return text == null ? null : memUTF16(text,true);
}
