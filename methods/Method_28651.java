public String clientGetname(){
  checkIsInMultiOrPipeline();
  client.clientGetname();
  return client.getBulkReply();
}
