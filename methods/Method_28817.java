public Response<Long> sinterstore(byte[] dstkey,byte[]... keys){
  client.sinterstore(dstkey,keys);
  return getResponse(BuilderFactory.LONG);
}
