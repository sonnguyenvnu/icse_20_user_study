public String getString(final String param){
  final int[] positions=query.getNamedParameterIndices(param);
  try {
    if (positions.length == 1) {
      return statement.getString(positions[0]);
    }
    throw newGetParamError(param);
  }
 catch (  SQLException sex) {
    throw newGetParamError(param,sex);
  }
}
