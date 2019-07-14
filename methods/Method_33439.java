private void hideError(){
  if (heightChanged) {
    new Timeline(new KeyFrame(Duration.millis(160),new KeyValue(translateYProperty(),0,Interpolator.EASE_BOTH))).play();
    new Timeline(new KeyFrame(Duration.millis(160),new KeyValue(minHeightProperty(),initHeight,Interpolator.EASE_BOTH))).play();
    heightChanged=false;
  }
  errorLabel.setText(null);
  oldErrorLabelHeight=errorLabelInitHeight;
  errorIcon.getChildren().clear();
  currentFieldHeight=initHeight;
  errorContainer.setVisible(false);
  errorShown=false;
}
