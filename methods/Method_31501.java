@Override protected Boolean detectCanExecuteInTransaction(String simplifiedStatement,List<Token> keywords){
  String current=keywords.get(keywords.size() - 1).getText();
  if ("BACKUP".equals(current) || "RESTORE".equals(current) || "RECONFIGURE".equals(current)) {
    return false;
  }
  if (keywords.size() < 2) {
    return null;
  }
  String previous=keywords.get(keywords.size() - 2).getText();
  if ("EXEC".equals(previous) && ("SP_ADDSUBSCRIPTION".equals(current) || "SP_DROPLINKEDSRVLOGIN".equals(current) || "SP_SERVEROPTION".equals(current))) {
    return false;
  }
  if (("CREATE".equals(previous) || "ALTER".equals(previous) || "DROP".equals(previous)) && ("DATABASE".equals(current) || "FULLTEXT".equals(current))) {
    return false;
  }
  return null;
}
