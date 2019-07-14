private DbSqlException newGetParamError(final int index,final Exception ex){
  return new DbSqlException("Invalid SQL parameter with index: " + index,ex);
}
