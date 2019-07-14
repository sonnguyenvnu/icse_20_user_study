static double computeXOffset(double width,double contentWidth,HPos hpos){
switch (hpos) {
case LEFT:
    return 0;
case CENTER:
  return (width - contentWidth) / 2;
case RIGHT:
return width - contentWidth;
}
return 0;
}
