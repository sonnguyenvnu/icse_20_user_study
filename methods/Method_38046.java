public byte[] getAllByte(final String param){
  final int[] positions=query.getNamedParameterIndices(param);
  final byte[] result=new byte[positions.length];
  try {
    for (int i=0; i < positions.length; i++) {
      result[i]=statement.getByte(positions[i]);
    }
    return result;
  }
 catch (  SQLException sex) {
    throw newGetParamError(param,sex);
  }
}
