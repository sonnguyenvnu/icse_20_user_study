public boolean set(YogaEdge yogaEdge,float value){
  final int edgeIntValue=yogaEdge.intValue();
  if (!floatsEqual(getRaw(edgeIntValue),value)) {
    final byte edgeIndex=getIndex(edgeIntValue);
    if (YogaConstants.isUndefined(value)) {
      mEdgesToValuesIndex|=((long)UNDEFINED_INDEX << (edgeIntValue * 4));
      mValues[edgeIndex]=YogaConstants.UNDEFINED;
    }
 else     if (edgeIndex == UNDEFINED_INDEX) {
      final byte newIndex=getFirstAvailableIndex();
      if (newIndex >= EDGES_LENGTH) {
        throw new IllegalStateException("The newIndex for the array cannot be bigger than the amount of Yoga Edges.");
      }
      mEdgesToValuesIndex&=~((long)(0xF) << (edgeIntValue * 4));
      mEdgesToValuesIndex|=((long)newIndex << (edgeIntValue * 4));
      mValues[newIndex]=value;
    }
 else {
      mValues[edgeIndex]=value;
    }
    mHasAliasesSet=(~((int)(mEdgesToValuesIndex >> ALIASES_RIGHT_SHIFT)) & ALIASES_MASK) != 0;
    return true;
  }
  return false;
}
