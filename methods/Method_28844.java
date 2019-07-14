@Override public Response<List<byte[]>> hvals(byte[] key){
  getClient(key).hvals(key);
  return getResponse(BuilderFactory.BYTE_ARRAY_LIST);
}
