public Q setInteger(final int index,final int value){
  initPrepared();
  try {
    preparedStatement.setInt(index,value);
  }
 catch (  SQLException sex) {
    throwSetParamError(index,sex);
  }
  return _this();
}
