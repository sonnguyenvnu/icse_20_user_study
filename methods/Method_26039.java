/** 
 * Gets the text for the delta argument to be added. 
 */
private String getDeltaArgument(VisitorState state,List<Type> argumentTypes){
  Type firstType=argumentTypes.get(0);
  Type secondType=argumentTypes.get(1);
  boolean doublePrecisionUsed=isDouble(state,firstType) || isDouble(state,secondType);
  return doublePrecisionUsed ? ", 0.0" : ", 0.0f";
}
