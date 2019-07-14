private DbSqlException newGetParamError(final String param){
  return new DbSqlException("Invalid number of parameter with name: " + param);
}
