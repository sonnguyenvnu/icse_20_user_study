private void dispatchVisibilityEvent(VisibilityOutput visibilityOutput,Class<?> visibilityEventType){
  if (visibilityEventType == VisibleEvent.class) {
    if (visibilityOutput.getVisibleEventHandler() != null) {
      EventDispatcherUtils.dispatchOnVisible(visibilityOutput.getVisibleEventHandler());
    }
  }
 else   if (visibilityEventType == InvisibleEvent.class) {
    if (visibilityOutput.getInvisibleEventHandler() != null) {
      EventDispatcherUtils.dispatchOnInvisible(visibilityOutput.getInvisibleEventHandler());
    }
  }
 else   if (visibilityEventType == FocusedVisibleEvent.class) {
    if (visibilityOutput.getFocusedEventHandler() != null) {
      EventDispatcherUtils.dispatchOnFocused(visibilityOutput.getFocusedEventHandler());
    }
  }
 else   if (visibilityEventType == UnfocusedVisibleEvent.class) {
    if (visibilityOutput.getUnfocusedEventHandler() != null) {
      EventDispatcherUtils.dispatchOnUnfocused(visibilityOutput.getUnfocusedEventHandler());
    }
  }
 else   if (visibilityEventType == FullImpressionVisibleEvent.class) {
    if (visibilityOutput.getFullImpressionEventHandler() != null) {
      EventDispatcherUtils.dispatchOnFullImpression(visibilityOutput.getFullImpressionEventHandler());
    }
  }
}
