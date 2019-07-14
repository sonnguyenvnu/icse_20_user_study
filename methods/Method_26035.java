/** 
 * Determines if the type is a numeric type, including reference types. <p>Type.isNumeric() does not handle reference types properly.
 */
private boolean isNumeric(VisitorState state,Type type){
  Type trueType=unboxedTypeOrType(state,type);
  return trueType.isNumeric();
}
