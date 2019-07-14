private Q registerOutParameter(final String param,final int type){
  initCallable();
  final int[] positions=query.getNamedParameterIndices(param);
  try {
    for (    final int position : positions) {
      callableStatement.registerOutParameter(position,type);
    }
  }
 catch (  SQLException sex) {
    throwSetParamError(param,sex);
  }
  return _this();
}
