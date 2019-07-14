/** 
 * Dispatch a visibility events to all the components hosted in this LithoView. <p>Marked as @Deprecated to indicate this method is experimental and should not be widely used. <p>NOTE: Can only be used when Incremental Mount is disabled! Call this method when the LithoView is considered eligible for the visibility event (i.e. only dispatch VisibleEvent when the LithoView is visible in its container).
 * @param visibilityEventType The class type of the visibility event to dispatch. Supported:VisibleEvent.class, InvisibleEvent.class, FocusedVisibleEvent.class, UnfocusedVisibleEvent.class, FullImpressionVisibleEvent.class.
 */
@Deprecated public void dispatchVisibilityEvent(Class<?> visibilityEventType){
  if (isIncrementalMountEnabled()) {
    throw new IllegalStateException("dispatchVisibilityEvent - " + "Can't manually trigger visibility events when incremental mount is enabled");
  }
  LayoutState layoutState=mComponentTree == null ? null : mComponentTree.getMainThreadLayoutState();
  if (layoutState != null && visibilityEventType != null) {
    for (int i=0; i < layoutState.getVisibilityOutputCount(); i++) {
      dispatchVisibilityEvent(layoutState.getVisibilityOutputAt(i),visibilityEventType);
    }
    List<LithoView> childViews=mMountState.getChildLithoViewsFromCurrentlyMountedItems();
    for (    LithoView lithoView : childViews) {
      lithoView.dispatchVisibilityEvent(visibilityEventType);
    }
  }
}
