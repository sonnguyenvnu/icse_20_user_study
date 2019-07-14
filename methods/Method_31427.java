@Override protected Boolean detectCanExecuteInTransaction(String simplifiedStatement,List<Token> keywords){
  if (CREATE_DATABASE_TABLESPACE_SUBSCRIPTION_REGEX.matcher(simplifiedStatement).matches() || ALTER_SYSTEM_REGEX.matcher(simplifiedStatement).matches() || CREATE_INDEX_CONCURRENTLY_REGEX.matcher(simplifiedStatement).matches() || REINDEX_REGEX.matcher(simplifiedStatement).matches() || VACUUM_REGEX.matcher(simplifiedStatement).matches() || DISCARD_ALL_REGEX.matcher(simplifiedStatement).matches() || ALTER_TYPE_ADD_VALUE_REGEX.matcher(simplifiedStatement).matches()) {
    return false;
  }
  return null;
}
