static void dispatchOnPopulateAccessibilityEvent(EventHandler<OnPopulateAccessibilityEventEvent> eventHandler,View host,AccessibilityEvent event,AccessibilityDelegateCompat superDelegate){
  assertMainThread();
  if (sOnPopulateAccessibilityEventEvent == null) {
    sOnPopulateAccessibilityEventEvent=new OnPopulateAccessibilityEventEvent();
  }
  sOnPopulateAccessibilityEventEvent.host=host;
  sOnPopulateAccessibilityEventEvent.event=event;
  sOnPopulateAccessibilityEventEvent.superDelegate=superDelegate;
  final EventDispatcher eventDispatcher=eventHandler.mHasEventDispatcher.getEventDispatcher();
  eventDispatcher.dispatchOnEvent(eventHandler,sOnPopulateAccessibilityEventEvent);
  sOnPopulateAccessibilityEventEvent.host=null;
  sOnPopulateAccessibilityEventEvent.event=null;
  sOnPopulateAccessibilityEventEvent.superDelegate=null;
}
