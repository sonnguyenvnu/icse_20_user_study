private float resolveHorizontalEdges(Edges spacing,YogaEdge edge){
  final boolean isRtl=(mYogaNode.getLayoutDirection() == YogaDirection.RTL);
  final YogaEdge resolvedEdge;
switch (edge) {
case LEFT:
    resolvedEdge=(isRtl ? YogaEdge.END : YogaEdge.START);
  break;
case RIGHT:
resolvedEdge=(isRtl ? YogaEdge.START : YogaEdge.END);
break;
default :
throw new IllegalArgumentException("Not an horizontal padding edge: " + edge);
}
float result=spacing.getRaw(resolvedEdge);
if (YogaConstants.isUndefined(result)) {
result=spacing.get(edge);
}
return result;
}
