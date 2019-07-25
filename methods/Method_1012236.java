/** 
 * ???????
 * @param text ???????
 * @return {@link SpanUtils}
 */
public SpanUtils append(@NonNull final CharSequence text){
  apply(mTypeCharSequence);
  mText=text;
  return this;
}
