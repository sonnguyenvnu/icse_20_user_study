private static void applyStyleToText(SpannableStringBuilder spannedText,WebvttCssStyle style,int start,int end){
  if (style == null) {
    return;
  }
  if (style.getStyle() != WebvttCssStyle.UNSPECIFIED) {
    spannedText.setSpan(new StyleSpan(style.getStyle()),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.isLinethrough()) {
    spannedText.setSpan(new StrikethroughSpan(),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.isUnderline()) {
    spannedText.setSpan(new UnderlineSpan(),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.hasFontColor()) {
    spannedText.setSpan(new ForegroundColorSpan(style.getFontColor()),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.hasBackgroundColor()) {
    spannedText.setSpan(new BackgroundColorSpan(style.getBackgroundColor()),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.getFontFamily() != null) {
    spannedText.setSpan(new TypefaceSpan(style.getFontFamily()),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (style.getTextAlign() != null) {
    spannedText.setSpan(new AlignmentSpan.Standard(style.getTextAlign()),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
switch (style.getFontSizeUnit()) {
case WebvttCssStyle.FONT_SIZE_UNIT_PIXEL:
    spannedText.setSpan(new AbsoluteSizeSpan((int)style.getFontSize(),true),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  break;
case WebvttCssStyle.FONT_SIZE_UNIT_EM:
spannedText.setSpan(new RelativeSizeSpan(style.getFontSize()),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
break;
case WebvttCssStyle.FONT_SIZE_UNIT_PERCENT:
spannedText.setSpan(new RelativeSizeSpan(style.getFontSize() / 100),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
break;
case WebvttCssStyle.UNSPECIFIED:
break;
}
}
