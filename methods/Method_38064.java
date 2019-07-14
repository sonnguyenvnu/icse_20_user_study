private void throwSetParamError(final String param,final Exception ex){
  throw new DbSqlException(this,"Invalid SQL parameter with name: " + param,ex);
}
