@Override public void scrollTo(int scroll){
  int scrollExtent=getScrollExtent();
  scroll=MathUtils.constrain(scroll,0,scrollExtent);
  if (mScroll == scroll) {
    return;
  }
  ViewUtils.setHeight(this,mMaxHeight - scroll);
  int oldScroll=mScroll;
  mScroll=scroll;
  if (oldScroll < scrollExtent && mScroll == scrollExtent) {
    StatusBarColorUtils.animateTo(mStatusBarColorFullscreen,AppUtils.getActivityFromContext(getContext()));
  }
 else   if (oldScroll == scrollExtent && mScroll < oldScroll) {
    StatusBarColorUtils.animateTo(mStatusBarColorScrim,AppUtils.getActivityFromContext(getContext()));
  }
}
