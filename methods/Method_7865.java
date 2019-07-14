private void setScaleToFill(){
  float bitmapWidth=centerImage.getBitmapWidth();
  float containerWidth=getContainerViewWidth();
  float bitmapHeight=centerImage.getBitmapHeight();
  float containerHeight=getContainerViewHeight();
  float scaleFit=Math.min(containerHeight / bitmapHeight,containerWidth / bitmapWidth);
  float width=(int)(bitmapWidth * scaleFit);
  float height=(int)(bitmapHeight * scaleFit);
  scale=Math.max(containerWidth / width,containerHeight / height);
  updateMinMax(scale);
}
