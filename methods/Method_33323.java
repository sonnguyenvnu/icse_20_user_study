@Override protected double computePrefWidth(double height,double topInset,double rightInset,double bottomInset,double leftInset){
  return super.computePrefWidth(height,topInset,rightInset,bottomInset,leftInset) + snapSize(radio.prefWidth(-1)) + padding / 3;
}
