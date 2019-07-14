public String clientSetname(final String name){
  checkIsInMultiOrPipeline();
  client.clientSetname(name);
  return client.getStatusCodeReply();
}
