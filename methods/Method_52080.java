/** 
 * Extracts the variable name by traversing PrimaryExpression -> PrimaryPrefix -> Name. Also check if there is a PrimaryExpression -> PrimarySuffix which indicates a field or array access.
 * @returns the Name or null if the PrimaryPrefix is "this" or "super"
 */
private ASTName singleVariableName(ASTPrimaryExpression primaryExpression){
  final ASTPrimaryPrefix primaryPrefix=primaryExpression.getFirstChildOfType(ASTPrimaryPrefix.class);
  final ASTPrimarySuffix primarySuffix=primaryExpression.getFirstChildOfType(ASTPrimarySuffix.class);
  if (primarySuffix != null || primaryPrefix == null) {
    return null;
  }
  return primaryPrefix.getFirstChildOfType(ASTName.class);
}
