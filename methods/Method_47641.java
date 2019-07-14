@Override protected void onSizeChanged(int width,int height,int oldWidth,int oldHeight){
  if (height < 8)   height=200;
  float baseSize=height / 8.0f;
  setScrollerBucketSize((int)baseSize);
  squareSpacing=dpToPixels(getContext(),1.0f);
  float maxTextSize=getDimension(getContext(),R.dimen.regularTextSize);
  float textSize=height * 0.06f;
  textSize=Math.min(textSize,maxTextSize);
  pSquareFg.setTextSize(textSize);
  pTextHeader.setTextSize(textSize);
  squareTextOffset=pSquareFg.getFontSpacing() * 0.4f;
  headerTextOffset=pTextHeader.getFontSpacing() * 0.3f;
  float rightLabelWidth=getWeekdayLabelWidth() + headerTextOffset;
  float horizontalPadding=getPaddingRight() + getPaddingLeft();
  columnWidth=baseSize;
  columnHeight=8 * baseSize;
  nColumns=(int)((width - rightLabelWidth - horizontalPadding) / baseSize) + 1;
  updateDate();
}
