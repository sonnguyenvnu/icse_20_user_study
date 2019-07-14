public double[] getAllDouble(final String param){
  final int[] positions=query.getNamedParameterIndices(param);
  final double[] result=new double[positions.length];
  try {
    for (int i=0; i < positions.length; i++) {
      result[i]=statement.getDouble(positions[i]);
    }
    return result;
  }
 catch (  SQLException sex) {
    throw newGetParamError(param,sex);
  }
}
