private void throwSetParamError(final int index,final Exception ex){
  throw new DbSqlException(this,"Invalid SQL parameter with index: " + index,ex);
}
