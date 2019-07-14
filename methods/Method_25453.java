@Override public boolean matches(T statement,VisitorState state){
  List<? extends StatementTree> blockStatements=state.findEnclosing(BlockTree.class).getStatements();
  int statementIndex=blockStatements.indexOf(statement);
  if (statementIndex == -1) {
    return false;
  }
  statementIndex++;
  StatementTree nextStmt=null;
  if (statementIndex < blockStatements.size()) {
    nextStmt=blockStatements.get(statementIndex);
  }
  return matcher.matches(nextStmt,state);
}
