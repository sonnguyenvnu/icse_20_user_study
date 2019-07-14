/** 
 * @param edgeEnumValue This method can directly accept the YogaEdge.XXX.intValue(). 
 */
public float getRaw(int edgeEnumValue){
  final byte edgeIndex=getIndex(edgeEnumValue);
  if (edgeIndex == UNDEFINED_INDEX) {
    return YogaConstants.UNDEFINED;
  }
  return mValues[edgeIndex];
}
