public float getRaw(YogaEdge edge){
  final byte edgeIndex=getIndex(edge.intValue());
  if (edgeIndex == UNDEFINED_INDEX) {
    return YogaConstants.UNDEFINED;
  }
  return mValues[edgeIndex];
}
