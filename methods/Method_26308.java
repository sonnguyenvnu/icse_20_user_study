/** 
 * Report a diagnostic for an instance of DCL on a local variable. A match is only reported if a non-volatile field is written to the variable after acquiring the lock and before the second null-check on the local. <p>e.g. <pre> {@code}if ($X == null)  synchronized (...) { $X = myNonVolatileField; if ($X == null) { ... } ... } } }</pre>
 */
private Description handleLocal(DCLInfo info,VisitorState state){
  JCExpressionStatement expr=getChild(info.synchTree().getBlock(),JCExpressionStatement.class);
  if (expr == null) {
    return Description.NO_MATCH;
  }
  if (expr.getStartPosition() > ((JCTree)info.innerIf()).getStartPosition()) {
    return Description.NO_MATCH;
  }
  if (!(expr.getExpression() instanceof JCAssign)) {
    return Description.NO_MATCH;
  }
  JCAssign assign=(JCAssign)expr.getExpression();
  if (!Objects.equals(ASTHelpers.getSymbol(assign.getVariable()),info.sym())) {
    return Description.NO_MATCH;
  }
  Symbol sym=ASTHelpers.getSymbol(assign.getExpression());
  if (!(sym instanceof VarSymbol)) {
    return Description.NO_MATCH;
  }
  VarSymbol fvar=(VarSymbol)sym;
  if (fvar.getKind() != ElementKind.FIELD) {
    return Description.NO_MATCH;
  }
  return handleField(info.outerIf(),fvar,state);
}
