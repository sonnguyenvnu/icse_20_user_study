public static <T>InvalidationListener addDelayedPropertyInvalidationListener(ObservableValue<T> property,Duration delayTime,BiConsumer<T,InvalidationListener> consumer){
  Wrapper<T> eventWrapper=new Wrapper<>();
  PauseTransition holdTimer=new PauseTransition(delayTime);
  final InvalidationListener invalidationListener=observable -> {
    eventWrapper.content=property.getValue();
    holdTimer.playFromStart();
  }
;
  holdTimer.setOnFinished(event -> consumer.accept(eventWrapper.content,invalidationListener));
  property.addListener(invalidationListener);
  return invalidationListener;
}
