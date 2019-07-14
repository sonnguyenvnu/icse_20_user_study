private static void attachFontFace(SpannableStringBuilder cueText,int fontFace,int defaultFontFace,int start,int end,int spanPriority){
  if (fontFace != defaultFontFace) {
    final int flags=Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | spanPriority;
    boolean isBold=(fontFace & FONT_FACE_BOLD) != 0;
    boolean isItalic=(fontFace & FONT_FACE_ITALIC) != 0;
    if (isBold) {
      if (isItalic) {
        cueText.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),start,end,flags);
      }
 else {
        cueText.setSpan(new StyleSpan(Typeface.BOLD),start,end,flags);
      }
    }
 else     if (isItalic) {
      cueText.setSpan(new StyleSpan(Typeface.ITALIC),start,end,flags);
    }
    boolean isUnderlined=(fontFace & FONT_FACE_UNDERLINE) != 0;
    if (isUnderlined) {
      cueText.setSpan(new UnderlineSpan(),start,end,flags);
    }
    if (!isUnderlined && !isBold && !isItalic) {
      cueText.setSpan(new StyleSpan(Typeface.NORMAL),start,end,flags);
    }
  }
}
