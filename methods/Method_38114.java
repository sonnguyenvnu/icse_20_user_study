public Q setAsciiStream(final String param,final InputStream stream,final int length){
  initPrepared();
  final int[] positions=query.getNamedParameterIndices(param);
  try {
    for (    final int position : positions) {
      preparedStatement.setAsciiStream(position,stream,length);
    }
  }
 catch (  SQLException sex) {
    throwSetParamError(param,sex);
  }
  return _this();
}
