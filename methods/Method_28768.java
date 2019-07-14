public List<String> pubsubChannels(String pattern){
  checkIsInMultiOrPipeline();
  client.pubsubChannels(pattern);
  return client.getMultiBulkReply();
}
