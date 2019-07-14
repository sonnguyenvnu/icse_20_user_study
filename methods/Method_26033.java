/** 
 * Determines if the invocation can be safely converted to JUnit 4 based on its argument types.
 */
private boolean canBeConvertedToJUnit4(VisitorState state,List<Type> argumentTypes){
  if (argumentTypes.size() > 2) {
    return true;
  }
  Type firstType=argumentTypes.get(0);
  Type secondType=argumentTypes.get(1);
  if (!isFloatingPoint(state,firstType) && !isFloatingPoint(state,secondType)) {
    return true;
  }
  if (!isNumeric(state,firstType) || !isNumeric(state,secondType)) {
    return true;
  }
  if (!firstType.isPrimitive() && !secondType.isPrimitive()) {
    return true;
  }
  return false;
}
