public Q setBigDecimal(final int index,final BigDecimal value){
  initPrepared();
  try {
    preparedStatement.setBigDecimal(index,value);
  }
 catch (  SQLException sex) {
    throwSetParamError(index,sex);
  }
  return _this();
}
