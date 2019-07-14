public Q setAsciiStream(final String param,final InputStream stream){
  initPrepared();
  final int[] positions=query.getNamedParameterIndices(param);
  try {
    for (    final int position : positions) {
      preparedStatement.setAsciiStream(position,stream,stream.available());
    }
  }
 catch (  IOException|SQLException ioex) {
    throwSetParamError(param,ioex);
  }
  return _this();
}
