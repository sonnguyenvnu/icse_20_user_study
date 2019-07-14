public Q setInteger(final String param,final int value){
  initPrepared();
  final int[] positions=query.getNamedParameterIndices(param);
  try {
    for (    final int position : positions) {
      preparedStatement.setInt(position,value);
    }
  }
 catch (  SQLException sex) {
    throwSetParamError(param,sex);
  }
  return _this();
}
