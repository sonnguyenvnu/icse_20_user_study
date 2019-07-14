@Override protected double computePrefHeight(double width){
  double gap=getGap();
  return super.computePrefHeight(width) + gap;
}
