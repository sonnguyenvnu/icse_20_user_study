@Override protected Boolean detectCanExecuteInTransaction(String simplifiedStatement,List<Token> keywords){
  if ("PRAGMA FOREIGN_KEYS".equals(simplifiedStatement)) {
    return false;
  }
  return null;
}
