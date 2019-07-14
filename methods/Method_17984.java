void showTooltip(LithoTooltip lithoTooltip,String anchorGlobalKey,int xOffset,int yOffset){
  assertMainThread();
  final Map<String,Rect> componentKeysToBounds;
synchronized (this) {
    componentKeysToBounds=mMainThreadLayoutState.getComponentKeyToBounds();
  }
  if (!componentKeysToBounds.containsKey(anchorGlobalKey)) {
    throw new IllegalArgumentException("Cannot find a component with key " + anchorGlobalKey + " to use as anchor.");
  }
  final Rect anchorBounds=componentKeysToBounds.get(anchorGlobalKey);
  lithoTooltip.showLithoTooltip(mLithoView,anchorBounds,xOffset,yOffset);
}
