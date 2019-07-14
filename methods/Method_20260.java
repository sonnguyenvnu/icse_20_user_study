boolean handleChanged(EpoxyViewHolder epoxyHolder,boolean visibilityChangedEnabled){
  boolean changed=false;
  if (visibleHeight != lastVisibleHeightNotified || visibleWidth != lastVisibleWidthNotified) {
    if (visibilityChangedEnabled) {
      epoxyHolder.visibilityChanged(100.f / height * visibleHeight,100.f / width * visibleWidth,visibleHeight,visibleWidth);
    }
    lastVisibleHeightNotified=visibleHeight;
    lastVisibleWidthNotified=visibleWidth;
    changed=true;
  }
  return changed;
}
