@Override protected double computeMaxWidth(double height,double topInset,double rightInset,double bottomInset,double leftInset){
  if (Region.USE_COMPUTED_SIZE == control.getRadius()) {
    return super.computeMaxHeight(height,topInset,rightInset,bottomInset,leftInset);
  }
 else {
    return control.getRadius() * 2 + arc.getStrokeWidth() * 2;
  }
}
