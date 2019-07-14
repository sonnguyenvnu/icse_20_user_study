/** 
 * Returns true if an accessibility event should not be dispatched now. This happens when an accessibility request arrives while RecyclerView does not have a stable state which is very hard to handle for a LayoutManager. Instead, this method records necessary information about the event and dispatches a window change event after the critical section is finished.
 * @return True if the accessibility event should be postponed.
 */
boolean shouldDeferAccessibilityEvent(AccessibilityEvent event){
  if (isComputingLayout()) {
    int type=0;
    if (event != null) {
      type=AccessibilityEventCompat.getContentChangeTypes(event);
    }
    if (type == 0) {
      type=AccessibilityEventCompat.CONTENT_CHANGE_TYPE_UNDEFINED;
    }
    mEatenAccessibilityChangeFlags|=type;
    return true;
  }
  return false;
}
