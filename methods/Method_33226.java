@Override protected double computePrefWidth(double height,double topInset,double rightInset,double bottomInset,double leftInset){
  return super.computePrefWidth(height,topInset,rightInset,bottomInset,leftInset) + snapSize(box.prefWidth(-1)) + labelOffset + 2 * padding;
}
