private static Matrix.ScaleToFit scaleTypeToScaleToFit(ScaleType st){
switch (st) {
case FIT_XY:
    return Matrix.ScaleToFit.FILL;
case FIT_START:
  return Matrix.ScaleToFit.START;
case FIT_CENTER:
return Matrix.ScaleToFit.CENTER;
case FIT_END:
return Matrix.ScaleToFit.END;
default :
throw new IllegalArgumentException("Only FIT_... values allowed");
}
}
