public static Statement wrap(Statement delegate,StatementInformation statementInformation,JdbcEventListener eventListener){
  if (delegate == null) {
    return null;
  }
  return new StatementWrapper(delegate,statementInformation,eventListener);
}
