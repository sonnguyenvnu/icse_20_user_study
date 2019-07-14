void clearVisibilityItems(){
  assertMainThread();
  boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection("MountState.clearVisibilityItems");
  }
  for (int i=mVisibilityIdToItemMap.size() - 1; i >= 0; i--) {
    final VisibilityItem visibilityItem=mVisibilityIdToItemMap.valueAt(i);
    if (visibilityItem.doNotClearInThisPass()) {
      visibilityItem.setDoNotClearInThisPass(false);
    }
 else {
      final EventHandler<InvisibleEvent> invisibleHandler=visibilityItem.getInvisibleHandler();
      final EventHandler<UnfocusedVisibleEvent> unfocusedHandler=visibilityItem.getUnfocusedHandler();
      final EventHandler<VisibilityChangedEvent> visibilityChangedHandler=visibilityItem.getVisibilityChangedHandler();
      if (invisibleHandler != null) {
        EventDispatcherUtils.dispatchOnInvisible(invisibleHandler);
      }
      if (visibilityItem.isInFocusedRange()) {
        visibilityItem.setFocusedRange(false);
        if (unfocusedHandler != null) {
          EventDispatcherUtils.dispatchOnUnfocused(unfocusedHandler);
        }
      }
      if (visibilityChangedHandler != null) {
        EventDispatcherUtils.dispatchOnVisibilityChanged(visibilityChangedHandler,0,0,0f,0f);
      }
      mVisibilityIdToItemMap.removeAt(i);
    }
  }
  if (isTracing) {
    ComponentsSystrace.endSection();
  }
}
