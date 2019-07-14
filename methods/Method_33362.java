private void switchTimeUnit(TimeUnit newVal){
  if (newVal == TimeUnit.HOURS) {
    Timeline fadeout=new Timeline(new KeyFrame(Duration.millis(320),new KeyValue(minutesContent.opacityProperty(),0,Interpolator.EASE_BOTH)));
    Timeline fadein=new Timeline(new KeyFrame(Duration.millis(320),new KeyValue(hoursContent.opacityProperty(),1,Interpolator.EASE_BOTH)));
    new ParallelTransition(fadeout,fadein).play();
  }
 else {
    Timeline fadeout=new Timeline(new KeyFrame(Duration.millis(320),new KeyValue(hoursContent.opacityProperty(),0,Interpolator.EASE_BOTH)));
    Timeline fadein=new Timeline(new KeyFrame(Duration.millis(320),new KeyValue(minutesContent.opacityProperty(),1,Interpolator.EASE_BOTH)));
    new ParallelTransition(fadeout,fadein).play();
  }
}
