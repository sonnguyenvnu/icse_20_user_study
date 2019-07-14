@Override public Response<List<byte[]>> geohash(byte[] key,byte[]... members){
  getClient(key).geohash(key,members);
  return getResponse(BuilderFactory.BYTE_ARRAY_LIST);
}
