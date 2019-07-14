@Override public float getLayoutPadding(YogaEdge edge){
  if (arr != null && ((int)arr[LAYOUT_EDGE_SET_FLAG_INDEX] & PADDING) == PADDING) {
    int paddingStartIndex=LAYOUT_PADDING_START_INDEX - ((((int)arr[LAYOUT_EDGE_SET_FLAG_INDEX] & MARGIN) == MARGIN) ? 0 : 4);
switch (edge) {
case LEFT:
      return arr[paddingStartIndex];
case TOP:
    return arr[paddingStartIndex + 1];
case RIGHT:
  return arr[paddingStartIndex + 2];
case BOTTOM:
return arr[paddingStartIndex + 3];
case START:
return getLayoutDirection() == YogaDirection.RTL ? arr[paddingStartIndex + 2] : arr[paddingStartIndex];
case END:
return getLayoutDirection() == YogaDirection.RTL ? arr[paddingStartIndex] : arr[paddingStartIndex + 2];
default :
throw new IllegalArgumentException("Cannot get layout paddings of multi-edge shorthands");
}
}
 else {
return 0;
}
}
