private void setRangeSize(int itemWidth,int itemHeight,int width,int height){
  mEstimatedViewportCount=Math.max(mLayoutInfo.approximateRangeSize(itemWidth,itemHeight,width,height),1);
}
