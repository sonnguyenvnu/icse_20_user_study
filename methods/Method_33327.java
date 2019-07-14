private void initializeVariables(){
  shifting=30 + thumb.getWidth();
  if (getSkinnable().getOrientation() != Orientation.HORIZONTAL) {
    horizontalRotation=-90;
  }
  if (((JFXSlider)getSkinnable()).getIndicatorPosition() != IndicatorPosition.LEFT) {
    indicatorRotation=180;
    shifting=-shifting;
  }
  final double rotationAngle=45;
  sliderValue.setRotate(rotationAngle + indicatorRotation + 3 * horizontalRotation);
  animatedThumb.setRotate(-rotationAngle + indicatorRotation + horizontalRotation);
}
