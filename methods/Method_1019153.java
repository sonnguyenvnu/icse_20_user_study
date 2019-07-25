@NonNull @Override SpannableStringBuilder format(@NonNull SpannableStringBuilder ssb,int lineNumber){
  if (isContainsAsterisk) {
    ssb=SyntaxUtils.parseBoldAndItalic(SyntaxKey.KEY_BOLD_ASTERISK,ssb,mCallback);
  }
  if (isContainsUnderline) {
    ssb=SyntaxUtils.parseBoldAndItalic(SyntaxKey.KEY_BOLD_UNDERLINE,ssb,mCallback);
  }
  return ssb;
}
