public Response<Long> sdiffstore(byte[] dstkey,byte[]... keys){
  client.sdiffstore(dstkey,keys);
  return getResponse(BuilderFactory.LONG);
}
