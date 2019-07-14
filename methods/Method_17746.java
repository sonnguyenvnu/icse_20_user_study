/** 
 * @param values values pertaining to {@link YogaEdge}s
 * @return whether the values are equal for each edge
 */
static boolean equalValues(int[] values){
  if (values.length != EDGE_COUNT) {
    throw new IllegalArgumentException("Given wrongly sized array");
  }
  int lastValue=values[0];
  for (int i=1, length=values.length; i < length; ++i) {
    if (lastValue != values[i]) {
      return false;
    }
  }
  return true;
}
