@Override public Object visit(ASTLocalVariableDeclaration node,Object data){
  final Set<String> loopVariables=new HashSet<>();
  for (  ASTVariableDeclaratorId declaratorId : node.findDescendantsOfType(ASTVariableDeclaratorId.class)) {
    loopVariables.add(declaratorId.getImage());
  }
  if (node.jjtGetParent() instanceof ASTForInit) {
    final ASTStatement loopBody=node.jjtGetParent().jjtGetParent().getFirstChildOfType(ASTStatement.class);
    final ForReassignOption forReassign=getProperty(FOR_REASSIGN);
    if (forReassign != ForReassignOption.ALLOW) {
      checkAssignExceptIncrement(data,loopVariables,loopBody);
      if (forReassign == ForReassignOption.SKIP) {
        checkIncrementAndDecrement(data,loopVariables,loopBody,IgnoreFlags.IGNORE_CONDITIONAL);
      }
 else {
        checkIncrementAndDecrement(data,loopVariables,loopBody);
      }
    }
  }
 else   if (node.jjtGetParent() instanceof ASTForStatement) {
    final ASTStatement loopBody=node.jjtGetParent().getFirstChildOfType(ASTStatement.class);
    final ForeachReassignOption foreachReassign=getProperty(FOREACH_REASSIGN);
    if (foreachReassign == ForeachReassignOption.FIRST_ONLY) {
      checkAssignExceptIncrement(data,loopVariables,loopBody,IgnoreFlags.IGNORE_FIRST);
      checkIncrementAndDecrement(data,loopVariables,loopBody,IgnoreFlags.IGNORE_FIRST);
    }
 else     if (foreachReassign == ForeachReassignOption.DENY) {
      checkAssignExceptIncrement(data,loopVariables,loopBody);
      checkIncrementAndDecrement(data,loopVariables,loopBody);
    }
  }
  return data;
}
