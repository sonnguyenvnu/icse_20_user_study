@Override SpannableStringBuilder format(@NonNull SpannableStringBuilder ssb,int lineNumber){
  return SyntaxUtils.parseBoldAndItalic(SyntaxKey.KEY_STRIKE_THROUGH,ssb,mCallback);
}
