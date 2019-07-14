@Override protected void createIndeterminateTimeline(){
  if (indeterminateTransition != null) {
    clearAnimation();
  }
  double dur=1;
  ProgressIndicator control=getSkinnable();
  final double w=control.getWidth() - (snappedLeftInset() + snappedRightInset());
  indeterminateTransition=new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(clip.scaleXProperty(),0.0,Interpolator.EASE_IN),new KeyValue(clip.translateXProperty(),-w / 2,Interpolator.LINEAR)),new KeyFrame(Duration.seconds(0.5 * dur),new KeyValue(clip.scaleXProperty(),0.4,Interpolator.LINEAR)),new KeyFrame(Duration.seconds(0.9 * dur),new KeyValue(clip.translateXProperty(),w / 2,Interpolator.LINEAR)),new KeyFrame(Duration.seconds(1 * dur),new KeyValue(clip.scaleXProperty(),0.0,Interpolator.EASE_OUT)));
  indeterminateTransition.setCycleCount(Timeline.INDEFINITE);
}
