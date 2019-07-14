@Override public float getLayoutMargin(YogaEdge edge){
switch (edge) {
case LEFT:
    return mMarginLeft;
case TOP:
  return mMarginTop;
case RIGHT:
return mMarginRight;
case BOTTOM:
return mMarginBottom;
case START:
return getLayoutDirection() == YogaDirection.RTL ? mMarginRight : mMarginLeft;
case END:
return getLayoutDirection() == YogaDirection.RTL ? mMarginLeft : mMarginRight;
default :
throw new IllegalArgumentException("Cannot get layout margins of multi-edge shorthands");
}
}
