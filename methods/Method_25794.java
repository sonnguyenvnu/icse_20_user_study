/** 
 * Validate that at most one of  {@code CheckReturnValue} and {@code CanIgnoreReturnValue} areapplied to a class (or interface or enum).
 */
@Override public Description matchClass(ClassTree tree,VisitorState state){
  if (hasDirectAnnotationWithSimpleName(ASTHelpers.getSymbol(tree),CHECK_RETURN_VALUE) && hasDirectAnnotationWithSimpleName(ASTHelpers.getSymbol(tree),CAN_IGNORE_RETURN_VALUE)) {
    return buildDescription(tree).setMessage(String.format(BOTH_ERROR,"class")).build();
  }
  return Description.NO_MATCH;
}
