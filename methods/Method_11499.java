@Override public void onPageScrolled(int position,float positionOffset,int positionOffsetPixels){
  this.mCurrentTab=position;
  this.mCurrentPositionOffset=positionOffset;
  scrollToCurrentTab();
  invalidate();
}
