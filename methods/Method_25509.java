/** 
 * Gets the symbol for a tree. Returns null if this tree does not have a symbol because it is of the wrong type, if  {@code tree} is null, or if the symbol cannot be found due to a compilationerror.
 */
public static Symbol getSymbol(Tree tree){
  if (tree instanceof JCFieldAccess) {
    return ((JCFieldAccess)tree).sym;
  }
  if (tree instanceof JCIdent) {
    return ((JCIdent)tree).sym;
  }
  if (tree instanceof JCMethodInvocation) {
    return ASTHelpers.getSymbol((MethodInvocationTree)tree);
  }
  if (tree instanceof JCNewClass) {
    return ASTHelpers.getSymbol((NewClassTree)tree);
  }
  if (tree instanceof MemberReferenceTree) {
    return ((JCMemberReference)tree).sym;
  }
  if (tree instanceof JCAnnotatedType) {
    return getSymbol(((JCAnnotatedType)tree).underlyingType);
  }
  if (tree instanceof ParameterizedTypeTree) {
    return getSymbol(((ParameterizedTypeTree)tree).getType());
  }
  if (tree instanceof ClassTree) {
    return getSymbol((ClassTree)tree);
  }
  return getDeclaredSymbol(tree);
}
