private void setupFontMetrics(CharSequence text,int start,int end,FontMetricsInt fm,Paint p){
  txtPaint.set(p);
  final CharacterStyle[] otherSpans=((Spanned)text).getSpans(start,end,CharacterStyle.class);
  for (  CharacterStyle otherSpan : otherSpans) {
    otherSpan.updateDrawState(txtPaint);
  }
  txtPaint.setTextSize(p.getTextSize());
  if (fm != null) {
    txtPaint.getFontMetricsInt(fm);
  }
}
