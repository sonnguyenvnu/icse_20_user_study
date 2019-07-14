@PostConstruct public void init(){
  Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(blueSpinner.progressProperty(),0),new KeyValue(greenSpinner.progressProperty(),0)),new KeyFrame(Duration.seconds(0.5),new KeyValue(greenSpinner.progressProperty(),0.5)),new KeyFrame(Duration.seconds(2),new KeyValue(blueSpinner.progressProperty(),1),new KeyValue(greenSpinner.progressProperty(),1)));
  timeline.setCycleCount(Timeline.INDEFINITE);
  timeline.play();
}
