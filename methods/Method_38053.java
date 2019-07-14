public long getLong(final String param){
  final int[] positions=query.getNamedParameterIndices(param);
  try {
    if (positions.length == 1) {
      return statement.getLong(positions[0]);
    }
    throw newGetParamError(param);
  }
 catch (  SQLException sex) {
    throw newGetParamError(param,sex);
  }
}
