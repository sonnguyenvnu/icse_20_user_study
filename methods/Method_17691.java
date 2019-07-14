@Override public float getLayoutMargin(YogaEdge edge){
  if (arr != null && ((int)arr[LAYOUT_EDGE_SET_FLAG_INDEX] & MARGIN) == MARGIN) {
switch (edge) {
case LEFT:
      return arr[LAYOUT_MARGIN_START_INDEX];
case TOP:
    return arr[LAYOUT_MARGIN_START_INDEX + 1];
case RIGHT:
  return arr[LAYOUT_MARGIN_START_INDEX + 2];
case BOTTOM:
return arr[LAYOUT_MARGIN_START_INDEX + 3];
case START:
return getLayoutDirection() == YogaDirection.RTL ? arr[LAYOUT_MARGIN_START_INDEX + 2] : arr[LAYOUT_MARGIN_START_INDEX];
case END:
return getLayoutDirection() == YogaDirection.RTL ? arr[LAYOUT_MARGIN_START_INDEX] : arr[LAYOUT_MARGIN_START_INDEX + 2];
default :
throw new IllegalArgumentException("Cannot get layout margins of multi-edge shorthands");
}
}
 else {
return 0;
}
}
