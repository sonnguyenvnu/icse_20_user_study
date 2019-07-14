public Q setString(final int index,final String value){
  initPrepared();
  try {
    preparedStatement.setString(index,value);
  }
 catch (  SQLException sex) {
    throwSetParamError(index,sex);
  }
  return _this();
}
