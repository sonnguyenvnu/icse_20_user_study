public SpannableBuilder foreground(final char text,@ColorInt int color){
  return append(text,new ForegroundColorSpan(color));
}
