public static <T>InvalidationListener addDelayedPropertyInvalidationListener(ObservableValue<T> property,Duration delayTime,Consumer<T> justInTimeConsumer,Consumer<T> delayedConsumer){
  Wrapper<T> eventWrapper=new Wrapper<>();
  PauseTransition holdTimer=new PauseTransition(delayTime);
  holdTimer.setOnFinished(event -> delayedConsumer.accept(eventWrapper.content));
  final InvalidationListener invalidationListener=observable -> {
    eventWrapper.content=property.getValue();
    justInTimeConsumer.accept(eventWrapper.content);
    holdTimer.playFromStart();
  }
;
  property.addListener(invalidationListener);
  return invalidationListener;
}
