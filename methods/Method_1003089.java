@Override public Expression optimize(Session session){
  throw DbException.get(ErrorCode.SYNTAX_ERROR_1,table);
}
