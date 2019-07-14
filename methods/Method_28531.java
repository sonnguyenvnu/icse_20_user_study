public SpannableBuilder background(final CharSequence text,final int color){
  if (!InputHelper.isEmpty(text))   return append(text,new BackgroundColorSpan(color));
  return this;
}
