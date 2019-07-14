private void onScrollStateChange(int scrollState){
  if (mScrollState == scrollState) {
    return;
  }
  mScrollState=scrollState;
  if (mOnScrollListener != null) {
    mOnScrollListener.onScrollStateChange(this,scrollState);
  }
  if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
    AccessibilityManager am=(AccessibilityManager)getContext().getSystemService(Context.ACCESSIBILITY_SERVICE);
    if (am.isTouchExplorationEnabled()) {
      String text=(mDisplayedValues == null) ? formatNumber(mValue) : mDisplayedValues[mValue - mMinValue];
      AccessibilityEvent event=AccessibilityEvent.obtain();
      event.setEventType(AccessibilityEvent.TYPE_ANNOUNCEMENT);
      event.getText().add(text);
      am.sendAccessibilityEvent(event);
    }
  }
}
