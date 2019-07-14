@Override protected void onSizeChanged(int width,int height,int oldWidth,int oldHeight){
  if (height < 9)   height=200;
  baseSize=height / 8;
  setScrollerBucketSize(baseSize);
  pText.setTextSize(baseSize * 0.4f);
  pGraph.setTextSize(baseSize * 0.4f);
  pGraph.setStrokeWidth(baseSize * 0.1f);
  pGrid.setStrokeWidth(baseSize * 0.05f);
  em=pText.getFontSpacing();
  columnWidth=baseSize;
  columnWidth=Math.max(columnWidth,getMaxMonthWidth() * 1.2f);
  columnHeight=8 * baseSize;
  nColumns=(int)(width / columnWidth);
  paddingTop=0;
}
