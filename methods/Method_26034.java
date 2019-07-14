/** 
 * Determines if the type is a floating-point type, including reference types. 
 */
private boolean isFloatingPoint(VisitorState state,Type type){
  Type trueType=unboxedTypeOrType(state,type);
  return (trueType.getKind() == TypeKind.DOUBLE) || (trueType.getKind() == TypeKind.FLOAT);
}
