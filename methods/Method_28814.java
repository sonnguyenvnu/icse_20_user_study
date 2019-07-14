public Response<Long> renamenx(byte[] oldkey,byte[] newkey){
  client.renamenx(oldkey,newkey);
  return getResponse(BuilderFactory.LONG);
}
