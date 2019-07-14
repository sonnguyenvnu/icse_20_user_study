@Override protected Boolean detectCanExecuteInTransaction(String simplifiedStatement,List<Token> keywords){
  if (CREATE_LIBRARY_REGEX.matcher(simplifiedStatement).matches() || CREATE_EXTERNAL_TABLE_REGEX.matcher(simplifiedStatement).matches() || VACUUM_REGEX.matcher(simplifiedStatement).matches() || ALTER_TABLE_APPEND_FROM_REGEX.matcher(simplifiedStatement).matches()) {
    return false;
  }
  return null;
}
