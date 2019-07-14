private Result visitStatements(Iterable<? extends StatementTree> nodes,BreakContext cxt){
  Result result=NEVER_EXITS;
  for (  StatementTree node : nodes) {
    result=result.then(node.accept(this,cxt));
  }
  return result;
}
