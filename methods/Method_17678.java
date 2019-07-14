@Override public float getLayoutBorder(YogaEdge edge){
switch (edge) {
case LEFT:
    return mBorderLeft;
case TOP:
  return mBorderTop;
case RIGHT:
return mBorderRight;
case BOTTOM:
return mBorderBottom;
case START:
return getLayoutDirection() == YogaDirection.RTL ? mBorderRight : mBorderLeft;
case END:
return getLayoutDirection() == YogaDirection.RTL ? mBorderLeft : mBorderRight;
default :
throw new IllegalArgumentException("Cannot get layout border of multi-edge shorthands");
}
}
