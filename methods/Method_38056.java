private DbSqlException newGetParamError(final String param,final Exception ex){
  return new DbSqlException("Invalid SQL parameter with name: " + param,ex);
}
