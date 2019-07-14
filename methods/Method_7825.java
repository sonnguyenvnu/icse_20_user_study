private void createPaint(boolean update){
  if (quoteLinePaint == null) {
    quoteLinePaint=new Paint();
    preformattedBackgroundPaint=new Paint();
    tableLinePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    tableLinePaint.setStyle(Paint.Style.STROKE);
    tableLinePaint.setStrokeWidth(AndroidUtilities.dp(1));
    tableHalfLinePaint=new Paint();
    tableHalfLinePaint.setStyle(Paint.Style.STROKE);
    tableHalfLinePaint.setStrokeWidth(AndroidUtilities.dp(1) / 2.0f);
    tableHeaderPaint=new Paint();
    tableStripPaint=new Paint();
    urlPaint=new Paint();
    webpageUrlPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    photoBackgroundPaint=new Paint();
    dividerPaint=new Paint();
    webpageMarkPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
  }
 else   if (!update) {
    return;
  }
  int color=getSelectedColor();
  if (color == 0) {
    preformattedBackgroundPaint.setColor(0xfff5f8fc);
    webpageUrlPaint.setColor(0xffebf3fa);
    urlPaint.setColor(0xffdceaf7);
    tableHalfLinePaint.setColor(0xffe0e0e0);
    tableLinePaint.setColor(0xffe0e0e0);
    tableHeaderPaint.setColor(0xfff4f4f4);
    tableStripPaint.setColor(0xfff7f7f7);
    photoBackgroundPaint.setColor(0xfff4f4f4);
    dividerPaint.setColor(0xffcdd1d5);
    webpageMarkPaint.setColor(0xfffef3bc);
  }
 else   if (color == 1) {
    preformattedBackgroundPaint.setColor(0xffe5dec8);
    webpageUrlPaint.setColor(0xffdbe6e7);
    urlPaint.setColor(0xffcadee6);
    tableHalfLinePaint.setColor(0xffc8c1b0);
    tableLinePaint.setColor(0xffc8c1b0);
    tableHeaderPaint.setColor(0xffeee6d0);
    tableStripPaint.setColor(0xffeee6d0);
    photoBackgroundPaint.setColor(0xffeee6d0);
    dividerPaint.setColor(0xffc1baa5);
    webpageMarkPaint.setColor(0xffe5ddcd);
  }
 else   if (color == 2) {
    preformattedBackgroundPaint.setColor(0xff1b1b1b);
    webpageUrlPaint.setColor(0xff222f38);
    urlPaint.setColor(0xff233846);
    tableHalfLinePaint.setColor(0xff2e2e2e);
    tableLinePaint.setColor(0xff2e2e2e);
    tableHeaderPaint.setColor(0xff1a1a1a);
    tableStripPaint.setColor(0xff1a1a1a);
    photoBackgroundPaint.setColor(0xff1c1c1c);
    dividerPaint.setColor(0xff444444);
    webpageMarkPaint.setColor(0xff242424);
  }
  quoteLinePaint.setColor(getTextColor());
}
