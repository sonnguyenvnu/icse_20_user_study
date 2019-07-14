private Description describe(MethodInvocationTree lockInvocation,TreePath statementPath,VisitorState state){
  Tree lockStatement=statementPath.getLeaf();
  ExpressionTree lockee=getReceiver(lockInvocation);
  if (lockee == null) {
    return NO_MATCH;
  }
  TryTree enclosingTry=state.findEnclosing(TryTree.class);
  if (enclosingTry != null && releases(enclosingTry,lockee,state)) {
    SuggestedFix fix=SuggestedFix.builder().replace(lockStatement,"").prefixWith(enclosingTry,state.getSourceForNode(lockStatement)).build();
    return buildDescription(lockInvocation).addFix(fix).setMessage(String.format("Prefer obtaining the lock for %s outside the try block. That way, if #lock" + " throws, the lock is not erroneously released.",state.getSourceForNode(getReceiver(lockInvocation)))).build();
  }
  Tree enclosing=state.getPath().getParentPath().getParentPath().getLeaf();
  if (!(enclosing instanceof BlockTree)) {
    return NO_MATCH;
  }
  BlockTree block=(BlockTree)enclosing;
  int index=block.getStatements().indexOf(lockStatement);
  for (  StatementTree statement : Iterables.skip(block.getStatements(),index + 1)) {
    if (statement instanceof TryTree && releases((TryTree)statement,lockee,state)) {
      SuggestedFix fix=SuggestedFix.builder().replace(lockStatement,"").prefixWith(statement,state.getSourceForNode(lockStatement)).build();
      return buildDescription(lockInvocation).addFix(fix).setMessage("Prefer locking *immediately* before the try block which releases the lock to" + " avoid the possibility of any intermediate statements throwing.").build();
    }
    if (statement instanceof ExpressionStatementTree) {
      ExpressionTree expression=((ExpressionStatementTree)statement).getExpression();
      if (acquires(expression,lockee,state)) {
        return buildDescription(lockInvocation).setMessage(String.format("Did you forget to release the lock on %s?",state.getSourceForNode(getReceiver(lockInvocation)))).build();
      }
      if (releases(expression,lockee,state)) {
        SuggestedFix fix=SuggestedFix.builder().postfixWith(lockStatement,"try {").prefixWith(statement,"} finally {").postfixWith(statement,"}").build();
        return buildDescription(lockInvocation).addFix(fix).setMessage(String.format("Prefer releasing the lock on %s inside a finally block.",state.getSourceForNode(getReceiver(lockInvocation)))).build();
      }
    }
  }
  return NO_MATCH;
}
