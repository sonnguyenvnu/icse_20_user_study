private static boolean blockEndsInBreakOrReturn(VisitorState state){
  TreePath statementPath=state.findPathToEnclosing(StatementTree.class);
  if (statementPath == null) {
    return false;
  }
  Tree parent=statementPath.getParentPath().getLeaf();
  if (!(parent instanceof BlockTree)) {
    return false;
  }
  StatementTree statement=(StatementTree)statementPath.getLeaf();
  List<? extends StatementTree> statements=((BlockTree)parent).getStatements();
  int idx=statements.indexOf(statement);
  if (idx == -1 || idx == statements.size()) {
    return false;
  }
switch (getLast(statements).getKind()) {
case BREAK:
case RETURN:
    return true;
default :
  return false;
}
}
