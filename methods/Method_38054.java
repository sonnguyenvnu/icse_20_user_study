public long[] getAllLong(final String param){
  final int[] positions=query.getNamedParameterIndices(param);
  final long[] result=new long[positions.length];
  try {
    for (int i=0; i < positions.length; i++) {
      result[i]=statement.getLong(positions[i]);
    }
    return result;
  }
 catch (  SQLException sex) {
    throw newGetParamError(param,sex);
  }
}
