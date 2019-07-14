public Q setDate(final String param,final Date value){
  initPrepared();
  final int[] positions=query.getNamedParameterIndices(param);
  try {
    for (    final int position : positions) {
      preparedStatement.setDate(position,value);
    }
  }
 catch (  SQLException sex) {
    throwSetParamError(param,sex);
  }
  return _this();
}
