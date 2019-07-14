public Q setDouble(final String param,final double value){
  initPrepared();
  final int[] positions=query.getNamedParameterIndices(param);
  try {
    for (    final int position : positions) {
      preparedStatement.setDouble(position,value);
    }
  }
 catch (  SQLException sex) {
    throwSetParamError(param,sex);
  }
  return _this();
}
