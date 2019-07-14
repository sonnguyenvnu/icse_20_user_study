public static <T>InvalidationListener addDelayedPropertyInvalidationListener(ObservableValue<T> property,Duration delayTime,Consumer<T> consumer){
  Wrapper<T> eventWrapper=new Wrapper<>();
  PauseTransition holdTimer=new PauseTransition(delayTime);
  holdTimer.setOnFinished(event -> consumer.accept(eventWrapper.content));
  final InvalidationListener invalidationListener=observable -> {
    eventWrapper.content=property.getValue();
    holdTimer.playFromStart();
  }
;
  property.addListener(invalidationListener);
  return invalidationListener;
}
