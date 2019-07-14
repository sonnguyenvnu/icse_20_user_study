/** 
 * Like  {@link #nASCII(CharSequence,boolean) nASCII}, but returns 0 if  {@code text} is {@code null}. 
 */
public int nASCIISafe(@Nullable CharSequence text,boolean nullTerminated){
  return text == null ? 0 : nASCII(text,nullTerminated);
}
