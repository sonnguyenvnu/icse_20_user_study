public Q setBoolean(final int index,final boolean value){
  initPrepared();
  try {
    preparedStatement.setBoolean(index,value);
  }
 catch (  SQLException sex) {
    throwSetParamError(index,sex);
  }
  return _this();
}
