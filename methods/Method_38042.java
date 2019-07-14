public int[] getAllInteger(final String param){
  final int[] positions=query.getNamedParameterIndices(param);
  final int[] result=new int[positions.length];
  try {
    for (int i=0; i < positions.length; i++) {
      result[i]=statement.getInt(positions[i]);
    }
    return result;
  }
 catch (  SQLException sex) {
    throw newGetParamError(param,sex);
  }
}
