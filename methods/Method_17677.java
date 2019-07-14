@Override public float getLayoutPadding(YogaEdge edge){
switch (edge) {
case LEFT:
    return mPaddingLeft;
case TOP:
  return mPaddingTop;
case RIGHT:
return mPaddingRight;
case BOTTOM:
return mPaddingBottom;
case START:
return getLayoutDirection() == YogaDirection.RTL ? mPaddingRight : mPaddingLeft;
case END:
return getLayoutDirection() == YogaDirection.RTL ? mPaddingLeft : mPaddingRight;
default :
throw new IllegalArgumentException("Cannot get layout paddings of multi-edge shorthands");
}
}
