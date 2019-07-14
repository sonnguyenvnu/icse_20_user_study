public Q setBytes(final int index,final byte[] value){
  initPrepared();
  try {
    preparedStatement.setBytes(index,value);
  }
 catch (  SQLException sex) {
    throwSetParamError(index,sex);
  }
  return _this();
}
