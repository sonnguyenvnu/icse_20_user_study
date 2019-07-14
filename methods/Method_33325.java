private static double computeYOffset(double height,double contentHeight,VPos vpos){
switch (vpos) {
case TOP:
    return 0;
case CENTER:
  return (height - contentHeight) / 2;
case BOTTOM:
return height - contentHeight;
default :
return 0;
}
}
