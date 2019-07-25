@Override SpannableStringBuilder format(@NonNull SpannableStringBuilder ssb,int lineNumber){
  String text=ssb.toString();
  return parse(text,ssb);
}
