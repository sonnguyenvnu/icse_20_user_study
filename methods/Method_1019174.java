@NonNull @Override SpannableStringBuilder format(@NonNull SpannableStringBuilder ssb,int lineNumber){
  if (isContainsAsterisk) {
    ssb=SyntaxUtils.parseBoldAndItalic(SyntaxKey.KEY_ITALIC_ASTERISK,ssb,mCallback);
  }
  if (isContainsUnderline) {
    ssb=SyntaxUtils.parseBoldAndItalic(SyntaxKey.KEY_ITALIC_UNDERLINE,ssb,mCallback);
  }
  return ssb;
}
