@Override protected double computePrefHeight(double width,double topInset,double rightInset,double bottomInset,double leftInset){
  final int itemsCount=getSkinnable().getItems().size();
  if (getSkinnable().maxHeightProperty().isBound() || itemsCount <= 0) {
    return super.computePrefHeight(width,topInset,rightInset,bottomInset,leftInset);
  }
  final double fixedCellSize=getSkinnable().getFixedCellSize();
  double computedHeight=fixedCellSize != Region.USE_COMPUTED_SIZE ? fixedCellSize * itemsCount + snapVerticalInsets() : estimateHeight();
  double height=super.computePrefHeight(width,topInset,rightInset,bottomInset,leftInset);
  if (height > computedHeight) {
    height=computedHeight;
  }
  if (getSkinnable().getMaxHeight() > 0 && computedHeight > getSkinnable().getMaxHeight()) {
    return getSkinnable().getMaxHeight();
  }
  return height;
}
