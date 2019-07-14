public static void applyStylesToSpan(SpannableStringBuilder builder,int start,int end,TtmlStyle style){
  if (style.getStyle() != TtmlStyle.UNSPECIFIED) {
    builder.setSpan(new StyleSpan(style.getStyle()),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.isLinethrough()) {
    builder.setSpan(new StrikethroughSpan(),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.isUnderline()) {
    builder.setSpan(new UnderlineSpan(),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.hasFontColor()) {
    builder.setSpan(new ForegroundColorSpan(style.getFontColor()),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.hasBackgroundColor()) {
    builder.setSpan(new BackgroundColorSpan(style.getBackgroundColor()),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.getFontFamily() != null) {
    builder.setSpan(new TypefaceSpan(style.getFontFamily()),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.getTextAlign() != null) {
    builder.setSpan(new AlignmentSpan.Standard(style.getTextAlign()),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
switch (style.getFontSizeUnit()) {
case TtmlStyle.FONT_SIZE_UNIT_PIXEL:
    builder.setSpan(new AbsoluteSizeSpan((int)style.getFontSize(),true),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  break;
case TtmlStyle.FONT_SIZE_UNIT_EM:
builder.setSpan(new RelativeSizeSpan(style.getFontSize()),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
break;
case TtmlStyle.FONT_SIZE_UNIT_PERCENT:
builder.setSpan(new RelativeSizeSpan(style.getFontSize() / 100),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
break;
case TtmlStyle.UNSPECIFIED:
break;
}
}
