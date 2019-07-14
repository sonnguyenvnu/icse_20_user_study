public void selectTabWithId(int id,float progress){
  int position=idToPosition.get(id,-1);
  if (position < 0) {
    return;
  }
  if (progress < 0) {
    progress=0;
  }
 else   if (progress > 1.0f) {
    progress=1.0f;
  }
  TextView child=(TextView)tabsContainer.getChildAt(currentPosition);
  TextView nextChild=(TextView)tabsContainer.getChildAt(position);
  if (child != null && nextChild != null) {
    animateIndicatorStartWidth=getChildWidth(child);
    animateIndicatorStartX=child.getLeft() + (child.getMeasuredWidth() - animateIndicatorStartWidth) / 2;
    animateIndicatorToWidth=getChildWidth(nextChild);
    animateIndicatorToX=nextChild.getLeft() + (nextChild.getMeasuredWidth() - animateIndicatorToWidth) / 2;
    setAnimationProgressInernal(nextChild,child,progress);
  }
  if (progress >= 1.0f) {
    currentPosition=position;
    selectedTabId=id;
  }
}
