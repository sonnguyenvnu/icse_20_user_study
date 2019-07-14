public List<String> time(){
  checkIsInMultiOrPipeline();
  client.time();
  return client.getMultiBulkReply();
}
