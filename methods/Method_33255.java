private void initColor(){
  final ColorPicker colorPicker=(ColorPicker)getSkinnable();
  Color color=colorPicker.getValue();
  Color circleColor=color == null ? Color.WHITE : color;
  colorBox.setBackground(new Background(new BackgroundFill(circleColor,new CornerRadii(3),Insets.EMPTY)));
  displayNode.setTextFill(circleColor.grayscale().getRed() < 0.5 ? Color.valueOf("rgba(255, 255, 255, 0.87)") : Color.valueOf("rgba(0, 0, 0, 0.87)"));
  if (colorLabelVisible.get()) {
    displayNode.setText(JFXNodeUtils.colorToHex(circleColor));
  }
 else {
    displayNode.setText("");
  }
}
