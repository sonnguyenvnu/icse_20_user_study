public static long getTemporaryVertexID(VertexIDType type,long count){
  Preconditions.checkArgument(type.isProper(),"Invalid vertex id type: %s",type);
  return makeTemporary(type.addPadding(count));
}
