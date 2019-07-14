public static <T extends Event>EventHandler<? super T> addDelayedEventHandler(Node control,Duration delayTime,final EventType<T> eventType,final EventHandler<? super T> eventHandler){
  Wrapper<T> eventWrapper=new Wrapper<>();
  PauseTransition holdTimer=new PauseTransition(delayTime);
  holdTimer.setOnFinished(finish -> eventHandler.handle(eventWrapper.content));
  final EventHandler<? super T> eventEventHandler=event -> {
    eventWrapper.content=event;
    holdTimer.playFromStart();
  }
;
  control.addEventHandler(eventType,eventEventHandler);
  return eventEventHandler;
}
