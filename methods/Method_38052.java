public String[] getAllString(final String param){
  final int[] positions=query.getNamedParameterIndices(param);
  final String[] result=new String[positions.length];
  try {
    for (int i=0; i < positions.length; i++) {
      result[i]=statement.getString(positions[i]);
    }
    return result;
  }
 catch (  SQLException sex) {
    throw newGetParamError(param,sex);
  }
}
