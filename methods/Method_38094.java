public Q setShort(final int index,final short value){
  initPrepared();
  try {
    preparedStatement.setShort(index,value);
  }
 catch (  SQLException sex) {
    throwSetParamError(index,sex);
  }
  return _this();
}
