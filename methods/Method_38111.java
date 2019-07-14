public Q setRef(final int index,final Ref value){
  initPrepared();
  try {
    preparedStatement.setRef(index,value);
  }
 catch (  SQLException sex) {
    throwSetParamError(index,sex);
  }
  return _this();
}
