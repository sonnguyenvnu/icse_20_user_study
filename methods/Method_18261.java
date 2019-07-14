private static ComparableDrawable getBorderColorDrawable(InternalNode node){
  if (!node.shouldDrawBorders()) {
    throw new RuntimeException("This node does not support drawing border color");
  }
  final boolean isRtl=node.recursivelyResolveLayoutDirection() == YogaDirection.RTL;
  final float[] borderRadius=node.getBorderRadius();
  final int[] borderColors=node.getBorderColors();
  final YogaEdge leftEdge=isRtl ? YogaEdge.RIGHT : YogaEdge.LEFT;
  final YogaEdge rightEdge=isRtl ? YogaEdge.LEFT : YogaEdge.RIGHT;
  return new BorderColorDrawable.Builder().pathEffect(node.getBorderPathEffect()).borderLeftColor(Border.getEdgeColor(borderColors,leftEdge)).borderTopColor(Border.getEdgeColor(borderColors,YogaEdge.TOP)).borderRightColor(Border.getEdgeColor(borderColors,rightEdge)).borderBottomColor(Border.getEdgeColor(borderColors,YogaEdge.BOTTOM)).borderLeftWidth(node.getLayoutBorder(leftEdge)).borderTopWidth(node.getLayoutBorder(YogaEdge.TOP)).borderRightWidth(node.getLayoutBorder(rightEdge)).borderBottomWidth(node.getLayoutBorder(YogaEdge.BOTTOM)).borderRadius(borderRadius).build();
}
