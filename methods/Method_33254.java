private void updateColor(){
  final ColorPicker colorPicker=(ColorPicker)getSkinnable();
  Color color=colorPicker.getValue();
  Color circleColor=color == null ? Color.WHITE : color;
  if (((JFXColorPicker)getSkinnable()).isDisableAnimation()) {
    JFXNodeUtils.updateBackground(colorBox.getBackground(),colorBox,circleColor);
  }
 else {
    Circle colorCircle=new Circle();
    colorCircle.setFill(circleColor);
    colorCircle.setManaged(false);
    colorCircle.setLayoutX(colorBox.getWidth() / 4);
    colorCircle.setLayoutY(colorBox.getHeight() / 2);
    colorBox.getChildren().add(colorCircle);
    Timeline animateColor=new Timeline(new KeyFrame(Duration.millis(240),new KeyValue(colorCircle.radiusProperty(),200,Interpolator.EASE_BOTH)));
    animateColor.setOnFinished((finish) -> {
      JFXNodeUtils.updateBackground(colorBox.getBackground(),colorBox,colorCircle.getFill());
      colorBox.getChildren().remove(colorCircle);
    }
);
    animateColor.play();
  }
  displayNode.setTextFill(circleColor.grayscale().getRed() < 0.5 ? Color.valueOf("rgba(255, 255, 255, 0.87)") : Color.valueOf("rgba(0, 0, 0, 0.87)"));
  if (colorLabelVisible.get()) {
    displayNode.setText(JFXNodeUtils.colorToHex(circleColor));
  }
 else {
    displayNode.setText("");
  }
}
