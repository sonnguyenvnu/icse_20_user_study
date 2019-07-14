@Override protected double computePrefWidth(double height,double topInset,double rightInset,double bottomInset,double leftInset){
  return Math.max(100,leftInset + bar.prefWidth(getSkinnable().getWidth()) + rightInset);
}
