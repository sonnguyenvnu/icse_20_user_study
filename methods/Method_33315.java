@Override protected double computeMaxHeight(double width,double topInset,double rightInset,double bottomInset,double leftInset){
  return getSkinnable().prefHeight(width);
}
