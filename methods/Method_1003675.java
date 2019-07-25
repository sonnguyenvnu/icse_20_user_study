public void send(ByteBuf buffer){
  contentTypeIfNotSet(HttpHeaderConstants.OCTET_STREAM);
  commit(buffer);
}
