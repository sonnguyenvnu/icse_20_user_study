@Override public int getSize(Paint paint,CharSequence text,int start,int end,Paint.FontMetricsInt fm){
  if (fm != null) {
    if (alignTop) {
      int h=fm.descent - fm.ascent - AndroidUtilities.dp(4);
      fm.bottom=fm.descent=height - h;
      fm.top=fm.ascent=0 - h;
    }
 else {
      fm.top=fm.ascent=(-height / 2) - AndroidUtilities.dp(4);
      fm.bottom=fm.descent=height - (height / 2) - AndroidUtilities.dp(4);
    }
  }
  return width;
}
