public String clientSetname(final byte[] name){
  checkIsInMultiOrPipeline();
  client.clientSetname(name);
  return client.getBulkReply();
}
