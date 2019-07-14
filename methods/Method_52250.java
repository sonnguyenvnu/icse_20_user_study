/** 
 * Checks all exceptions for possible violation on the exception declaration.
 * @param exceptionList containing all exception for declaration
 * @param context
 */
private void evaluateExceptions(List<ASTName> exceptionList,Object context){
  for (  ASTName exception : exceptionList) {
    if (hasDeclaredExceptionInSignature(exception)) {
      addViolation(context,exception);
    }
  }
}
