/** 
 * Matches an instance of DCL. The canonical pattern is: <pre> {@code}if ($X == null)  synchronized (...) { if ($X == null) { ... } ... } } }</pre> Gaps before the synchronized or inner 'if' statement are ignored, and the operands in the null-checks are accepted in either order.
 */
@Nullable static DCLInfo findDCL(IfTree outerIf){
  ExpressionTree outerIfTest=getNullCheckedExpression(outerIf.getCondition());
  if (outerIfTest == null) {
    return null;
  }
  SynchronizedTree synchTree=getChild(outerIf.getThenStatement(),SynchronizedTree.class);
  if (synchTree == null) {
    return null;
  }
  IfTree innerIf=getChild(synchTree.getBlock(),IfTree.class);
  if (innerIf == null) {
    return null;
  }
  ExpressionTree innerIfTest=getNullCheckedExpression(innerIf.getCondition());
  if (innerIfTest == null) {
    return null;
  }
  Symbol outerSym=ASTHelpers.getSymbol(outerIfTest);
  if (!Objects.equals(outerSym,ASTHelpers.getSymbol(innerIfTest))) {
    return null;
  }
  if (!(outerSym instanceof VarSymbol)) {
    return null;
  }
  VarSymbol var=(VarSymbol)outerSym;
  return DCLInfo.create(outerIf,synchTree,innerIf,var);
}
