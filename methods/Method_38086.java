public Q setBytes(final String param,final byte[] value){
  initPrepared();
  final int[] positions=query.getNamedParameterIndices(param);
  try {
    for (    final int position : positions) {
      preparedStatement.setBytes(position,value);
    }
  }
 catch (  SQLException sex) {
    throwSetParamError(param,sex);
  }
  return _this();
}
