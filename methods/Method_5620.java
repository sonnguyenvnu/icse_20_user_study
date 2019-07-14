@SuppressWarnings("ReferenceEquality") private static void attachFontFamily(SpannableStringBuilder cueText,String fontFamily,String defaultFontFamily,int start,int end,int spanPriority){
  if (fontFamily != defaultFontFamily) {
    cueText.setSpan(new TypefaceSpan(fontFamily),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | spanPriority);
  }
}
