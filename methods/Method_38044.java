public boolean[] getAllBoolean(final String param){
  final int[] positions=query.getNamedParameterIndices(param);
  final boolean[] result=new boolean[positions.length];
  try {
    for (int i=0; i < positions.length; i++) {
      result[i]=statement.getBoolean(positions[i]);
    }
    return result;
  }
 catch (  SQLException sex) {
    throw newGetParamError(param,sex);
  }
}
