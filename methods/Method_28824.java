public Response<Long> bitop(BitOP op,byte[] destKey,byte[]... srcKeys){
  client.bitop(op,destKey,srcKeys);
  return getResponse(BuilderFactory.LONG);
}
