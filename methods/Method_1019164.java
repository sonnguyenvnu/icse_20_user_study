@NonNull @Override SpannableStringBuilder format(@NonNull SpannableStringBuilder ssb,int lineNumber){
  String text=ssb.toString();
  if (text.startsWith(SyntaxKey.KEY_5_HEADER)) {
    ssb.delete(0,SyntaxKey.KEY_5_HEADER.length());
    ssb.setSpan(new RelativeSizeSpan(mHeader6RelativeSize),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
 else   if (text.startsWith(SyntaxKey.KEY_4_HEADER)) {
    ssb.delete(0,SyntaxKey.KEY_4_HEADER.length());
    ssb.setSpan(new RelativeSizeSpan(mHeader5RelativeSize),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
 else   if (text.startsWith(SyntaxKey.KEY_3_HEADER)) {
    ssb.delete(0,SyntaxKey.KEY_3_HEADER.length());
    ssb.setSpan(new RelativeSizeSpan(mHeader4RelativeSize),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
 else   if (text.startsWith(SyntaxKey.KEY_2_HEADER)) {
    ssb.delete(0,SyntaxKey.KEY_2_HEADER.length());
    ssb.setSpan(new RelativeSizeSpan(mHeader3RelativeSize),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
 else   if (text.startsWith(SyntaxKey.KEY_1_HEADER)) {
    ssb.delete(0,SyntaxKey.KEY_1_HEADER.length());
    ssb.setSpan(new RelativeSizeSpan(mHeader2RelativeSize),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
 else   if (text.startsWith(SyntaxKey.KEY_0_HEADER)) {
    ssb.delete(0,SyntaxKey.KEY_0_HEADER.length());
    ssb.setSpan(new RelativeSizeSpan(mHeader1RelativeSize),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  return ssb;
}
