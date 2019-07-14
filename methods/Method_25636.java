/** 
 * Checks that the return value of a functional interface is void. Note, we do not use ASTHelpers.isVoidType here, return values of Void are actually type-checked. Only void-returning functions silently ignore return values of any type.
 */
private static boolean functionalInterfaceReturnsExactlyVoid(Type interfaceType,VisitorState state){
  return state.getTypes().findDescriptorType(interfaceType).getReturnType().getKind() == TypeKind.VOID;
}
