/** 
 * Returns the precedence level appropriate for unambiguously printing leaf as a subexpression of its parent.
 */
private static int getPrecedence(JCTree leaf,Context context){
  JCCompilationUnit comp=context.get(JCCompilationUnit.class);
  JCTree parent=TreeInfo.pathFor(leaf,comp).get(1);
  if (parent instanceof JCConditional) {
    JCConditional conditional=(JCConditional)parent;
    return TreeInfo.condPrec + ((conditional.cond == leaf) ? 1 : 0);
  }
 else   if (parent instanceof JCAssign) {
    JCAssign assign=(JCAssign)parent;
    return TreeInfo.assignPrec + ((assign.lhs == leaf) ? 1 : 0);
  }
 else   if (parent instanceof JCAssignOp) {
    JCAssignOp assignOp=(JCAssignOp)parent;
    return TreeInfo.assignopPrec + ((assignOp.lhs == leaf) ? 1 : 0);
  }
 else   if (parent instanceof JCUnary) {
    return TreeInfo.opPrec(parent.getTag());
  }
 else   if (parent instanceof JCBinary) {
    JCBinary binary=(JCBinary)parent;
    return TreeInfo.opPrec(parent.getTag()) + ((binary.rhs == leaf) ? 1 : 0);
  }
 else   if (parent instanceof JCTypeCast) {
    JCTypeCast typeCast=(JCTypeCast)parent;
    return (typeCast.expr == leaf) ? TreeInfo.prefixPrec : TreeInfo.noPrec;
  }
 else   if (parent instanceof JCInstanceOf) {
    JCInstanceOf instanceOf=(JCInstanceOf)parent;
    return TreeInfo.ordPrec + ((instanceOf.clazz == leaf) ? 1 : 0);
  }
 else   if (parent instanceof JCArrayAccess) {
    JCArrayAccess arrayAccess=(JCArrayAccess)parent;
    return (arrayAccess.indexed == leaf) ? TreeInfo.postfixPrec : TreeInfo.noPrec;
  }
 else   if (parent instanceof JCFieldAccess) {
    JCFieldAccess fieldAccess=(JCFieldAccess)parent;
    return (fieldAccess.selected == leaf) ? TreeInfo.postfixPrec : TreeInfo.noPrec;
  }
 else {
    return TreeInfo.noPrec;
  }
}
