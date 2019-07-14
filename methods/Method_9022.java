@Keep public void setAnimationIdicatorProgress(float value){
  animationIdicatorProgress=value;
  TextView newTab=(TextView)tabsContainer.getChildAt(currentPosition);
  TextView prevTab=(TextView)tabsContainer.getChildAt(previousPosition);
  setAnimationProgressInernal(newTab,prevTab,value);
  if (delegate != null) {
    delegate.onPageScrolled(value);
  }
}
