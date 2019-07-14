private static void attachColor(SpannableStringBuilder cueText,int colorRgba,int defaultColorRgba,int start,int end,int spanPriority){
  if (colorRgba != defaultColorRgba) {
    int colorArgb=((colorRgba & 0xFF) << 24) | (colorRgba >>> 8);
    cueText.setSpan(new ForegroundColorSpan(colorArgb),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | spanPriority);
  }
}
