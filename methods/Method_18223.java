static void dispatchOnInitializeAccessibilityEvent(EventHandler<OnInitializeAccessibilityEventEvent> eventHandler,View host,AccessibilityEvent event,AccessibilityDelegateCompat superDelegate){
  assertMainThread();
  if (sOnInitializeAccessibilityEventEvent == null) {
    sOnInitializeAccessibilityEventEvent=new OnInitializeAccessibilityEventEvent();
  }
  sOnInitializeAccessibilityEventEvent.host=host;
  sOnInitializeAccessibilityEventEvent.event=event;
  sOnInitializeAccessibilityEventEvent.superDelegate=superDelegate;
  final EventDispatcher eventDispatcher=eventHandler.mHasEventDispatcher.getEventDispatcher();
  eventDispatcher.dispatchOnEvent(eventHandler,sOnInitializeAccessibilityEventEvent);
  sOnInitializeAccessibilityEventEvent.host=null;
  sOnInitializeAccessibilityEventEvent.event=null;
  sOnInitializeAccessibilityEventEvent.superDelegate=null;
}
