private double computeTranslation(double circleRadius,Line line){
  return (getSkinnable().isSelected() ? 1 : -1) * ((line.getLayoutBounds().getWidth() / 2) - circleRadius + 2);
}
