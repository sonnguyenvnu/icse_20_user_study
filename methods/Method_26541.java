@Override public Result visitIf(IfTree node,BreakContext cxt){
  Result thenResult=node.getThenStatement().accept(this,cxt);
  Result elseResult=(node.getElseStatement() == null) ? NEVER_EXITS : node.getElseStatement().accept(this,cxt);
  return thenResult.or(elseResult);
}
