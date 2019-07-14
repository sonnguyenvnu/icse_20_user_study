/** 
 * Checks that the return value of a functional interface is void. Note, we do not use ASTHelpers.isVoidType here, return values of Void are actually type-checked. Only void-returning functions silently ignore return values of any type.
 */
private static boolean functionalInterfaceReturnsObject(Type interfaceType,VisitorState state){
  Type objectType=state.getTypeFromString("java.lang.Object");
  return ASTHelpers.isSubtype(objectType,ASTHelpers.getUpperBound(state.getTypes().findDescriptorType(interfaceType).getReturnType(),state.getTypes()),state);
}
