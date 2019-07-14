/** 
 * Validate  {@code @CheckReturnValue} and {@link CanIgnoreReturnValue} usage on methods.<p>The annotations should not both be applied to the same method. <p>The annotations should not be applied to void-returning methods. Doing so makes no sense, because there is no return value to check.
 */
@Override public Description matchMethod(MethodTree tree,VisitorState state){
  MethodSymbol method=ASTHelpers.getSymbol(tree);
  boolean checkReturn=hasDirectAnnotationWithSimpleName(method,CHECK_RETURN_VALUE);
  boolean canIgnore=hasDirectAnnotationWithSimpleName(method,CAN_IGNORE_RETURN_VALUE);
  if (checkReturn && canIgnore) {
    return buildDescription(tree).setMessage(String.format(BOTH_ERROR,"method")).build();
  }
  String annotationToValidate;
  if (checkReturn) {
    annotationToValidate=CHECK_RETURN_VALUE;
  }
 else   if (canIgnore) {
    annotationToValidate=CAN_IGNORE_RETURN_VALUE;
  }
 else {
    return Description.NO_MATCH;
  }
  if (method.getKind() != ElementKind.METHOD) {
    return Description.NO_MATCH;
  }
  if (!ASTHelpers.isVoidType(method.getReturnType(),state)) {
    return Description.NO_MATCH;
  }
  String message=String.format("@%s may not be applied to void-returning methods",annotationToValidate);
  return buildDescription(tree).setMessage(message).build();
}
