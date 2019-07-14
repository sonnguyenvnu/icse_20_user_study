public float[] getAllFloat(final String param){
  final int[] positions=query.getNamedParameterIndices(param);
  final float[] result=new float[positions.length];
  try {
    for (int i=0; i < positions.length; i++) {
      result[i]=statement.getFloat(positions[i]);
    }
    return result;
  }
 catch (  SQLException sex) {
    throw newGetParamError(param,sex);
  }
}
