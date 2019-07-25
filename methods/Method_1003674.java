public void send(byte[] bytes){
  contentTypeIfNotSet(HttpHeaderConstants.OCTET_STREAM);
  commit(Unpooled.wrappedBuffer(bytes));
}
