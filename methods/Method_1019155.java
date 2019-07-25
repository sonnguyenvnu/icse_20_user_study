@NonNull @Override SpannableStringBuilder format(@NonNull SpannableStringBuilder ssb,int lineNumber){
  ssb.delete(0,1).delete(ssb.length() - 1,ssb.length());
  ssb.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  return ssb;
}
