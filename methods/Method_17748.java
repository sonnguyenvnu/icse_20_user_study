private static int edgeIndex(YogaEdge edge){
switch (edge) {
case START:
case LEFT:
    return EDGE_LEFT;
case TOP:
  return EDGE_TOP;
case END:
case RIGHT:
return EDGE_RIGHT;
case BOTTOM:
return EDGE_BOTTOM;
case HORIZONTAL:
case VERTICAL:
case ALL:
}
throw new IllegalArgumentException("Given unsupported edge " + edge.name());
}
