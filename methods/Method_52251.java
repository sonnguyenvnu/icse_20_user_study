/** 
 * Checks if the given value is defined as <code>Exception</code> and the parent is either a method or constructor declaration.
 * @param exception to evaluate
 * @return true if <code>Exception</code> is declared and has proper parents
 */
private boolean hasDeclaredExceptionInSignature(ASTName exception){
  return exception.hasImageEqualTo("Exception") && isParentSignatureDeclaration(exception);
}
