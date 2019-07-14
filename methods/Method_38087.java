public Q setDouble(final int index,final double value){
  initPrepared();
  try {
    preparedStatement.setDouble(index,value);
  }
 catch (  SQLException sex) {
    throwSetParamError(index,sex);
  }
  return _this();
}
