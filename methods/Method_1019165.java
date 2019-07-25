@Override SpannableStringBuilder format(@NonNull SpannableStringBuilder ssb,int lineNumber){
  ssb.replace(0,ssb.length()," ");
  ssb.setSpan(new MDHorizontalRulesSpan(mColor,mHeight),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  return ssb;
}
