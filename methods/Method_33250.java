@Override protected double computePrefHeight(double width,double topInset,double rightInset,double bottomInset,double leftInset){
  if (colorBox == null) {
    updateDisplayArea();
  }
  return topInset + colorBox.prefHeight(width) + bottomInset;
}
