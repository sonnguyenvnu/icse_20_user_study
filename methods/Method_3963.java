private void dispatchContentChangedIfNecessary(){
  final int flags=mEatenAccessibilityChangeFlags;
  mEatenAccessibilityChangeFlags=0;
  if (flags != 0 && isAccessibilityEnabled()) {
    final AccessibilityEvent event=AccessibilityEvent.obtain();
    event.setEventType(AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED);
    AccessibilityEventCompat.setContentChangeTypes(event,flags);
    sendAccessibilityEventUnchecked(event);
  }
}
