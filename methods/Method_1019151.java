@NonNull @Override SpannableStringBuilder format(@NonNull SpannableStringBuilder ssb,int lineNumber){
  int nested=calculateNested(ssb.toString());
  if (nested == 0) {
    return ssb;
  }
  int i=0;
  while (i < ssb.length()) {
    if (ssb.charAt(i) == '>' || ssb.charAt(i) == ' ') {
      i++;
    }
 else {
      break;
    }
  }
  final int number=i / 2;
  for (int n=0; n < number; n++) {
    ssb.replace(2 * n,2 * (n + 1),"  ");
  }
  ssb.setSpan(new MDQuoteSpan(mColor,nested),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | (2 << Spanned.SPAN_PRIORITY_SHIFT));
  ssb.setSpan(new MDQuoteBackgroundSpan(nested,bgColorList),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | (1 << Spanned.SPAN_PRIORITY_SHIFT));
  if (mRelativeSize != 1f) {
    ssb.setSpan(new RelativeSizeSpan(mRelativeSize),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  SyntaxUtils.marginSSBLeft(ssb,32);
  return ssb;
}
