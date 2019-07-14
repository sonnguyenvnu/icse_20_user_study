static YogaEdge edgeFromIndex(int i){
  if (i < 0 || i >= EDGE_COUNT) {
    throw new IllegalArgumentException("Given index out of range of acceptable edges: " + i);
  }
switch (i) {
case EDGE_LEFT:
    return YogaEdge.LEFT;
case EDGE_TOP:
  return YogaEdge.TOP;
case EDGE_RIGHT:
return YogaEdge.RIGHT;
case EDGE_BOTTOM:
return YogaEdge.BOTTOM;
}
throw new IllegalArgumentException("Given unknown edge index: " + i);
}
