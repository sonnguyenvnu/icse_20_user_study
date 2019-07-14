public Response<Long> sunionstore(byte[] dstkey,byte[]... keys){
  client.sunionstore(dstkey,keys);
  return getResponse(BuilderFactory.LONG);
}
