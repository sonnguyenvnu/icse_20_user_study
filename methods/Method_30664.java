private float getFraction(){
  int scrollExtent=getScrollExtent();
  return scrollExtent > 0 ? (float)mScroll / scrollExtent : 0;
}
