@Override public float getLayoutBorder(YogaEdge edge){
  if (arr != null && ((int)arr[LAYOUT_EDGE_SET_FLAG_INDEX] & BORDER) == BORDER) {
    int borderStartIndex=LAYOUT_BORDER_START_INDEX - ((((int)arr[LAYOUT_EDGE_SET_FLAG_INDEX] & MARGIN) == MARGIN) ? 0 : 4) - ((((int)arr[LAYOUT_EDGE_SET_FLAG_INDEX] & PADDING) == PADDING) ? 0 : 4);
switch (edge) {
case LEFT:
      return arr[borderStartIndex];
case TOP:
    return arr[borderStartIndex + 1];
case RIGHT:
  return arr[borderStartIndex + 2];
case BOTTOM:
return arr[borderStartIndex + 3];
case START:
return getLayoutDirection() == YogaDirection.RTL ? arr[borderStartIndex + 2] : arr[borderStartIndex];
case END:
return getLayoutDirection() == YogaDirection.RTL ? arr[borderStartIndex] : arr[borderStartIndex + 2];
default :
throw new IllegalArgumentException("Cannot get layout border of multi-edge shorthands");
}
}
 else {
return 0;
}
}
