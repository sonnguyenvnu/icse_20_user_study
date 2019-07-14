@Override public String getRange(JCCompilationUnit unit){
  try {
    CharSequence sequence=unit.getSourceFile().getCharContent(true);
    JCTree firstStatement=statements.get(0);
    JCTree lastStatement=Iterables.getLast(statements);
    return sequence.subSequence(firstStatement.getStartPosition(),lastStatement.getEndPosition(unit.endPositions)).toString();
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}
