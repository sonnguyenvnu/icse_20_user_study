static boolean dispatchDispatchPopulateAccessibilityEvent(EventHandler<DispatchPopulateAccessibilityEventEvent> eventHandler,View host,AccessibilityEvent event,AccessibilityDelegateCompat superDelegate){
  assertMainThread();
  if (sDispatchPopulateAccessibilityEventEvent == null) {
    sDispatchPopulateAccessibilityEventEvent=new DispatchPopulateAccessibilityEventEvent();
  }
  sDispatchPopulateAccessibilityEventEvent.host=host;
  sDispatchPopulateAccessibilityEventEvent.event=event;
  sDispatchPopulateAccessibilityEventEvent.superDelegate=superDelegate;
  final EventDispatcher eventDispatcher=eventHandler.mHasEventDispatcher.getEventDispatcher();
  final Object returnValue=eventDispatcher.dispatchOnEvent(eventHandler,sDispatchPopulateAccessibilityEventEvent);
  sDispatchPopulateAccessibilityEventEvent.host=null;
  sDispatchPopulateAccessibilityEventEvent.event=null;
  sDispatchPopulateAccessibilityEventEvent.superDelegate=null;
  return returnValue != null && (boolean)returnValue;
}
