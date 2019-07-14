private Timeline getTimeline(Duration timeout){
  Timeline animation;
  animation=new Timeline(new KeyFrame(Duration.ZERO,e -> this.toBack(),new KeyValue(this.visibleProperty(),false,Interpolator.EASE_BOTH),new KeyValue(this.translateYProperty(),this.getLayoutBounds().getHeight(),easeInterpolator),new KeyValue(this.opacityProperty(),0,easeInterpolator)),new KeyFrame(Duration.millis(10),e -> this.toFront(),new KeyValue(this.visibleProperty(),true,Interpolator.EASE_BOTH)),new KeyFrame(Duration.millis(300),new KeyValue(this.opacityProperty(),1,easeInterpolator),new KeyValue(this.translateYProperty(),0,easeInterpolator)));
  animation.setCycleCount(1);
  pauseTransition=Duration.INDEFINITE.equals(timeout) ? null : new PauseTransition(timeout);
  if (pauseTransition != null) {
    animation.setOnFinished(finish -> {
      pauseTransition.setOnFinished(done -> {
        pauseTransition=null;
        eventsSet.remove(currentEvent);
        currentEvent=eventQueue.peek();
        close();
      }
);
      pauseTransition.play();
    }
);
  }
  return animation;
}
