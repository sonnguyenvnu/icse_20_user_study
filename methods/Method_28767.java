@Override public List<Object> clusterSlots(){
  checkIsInMultiOrPipeline();
  client.clusterSlots();
  return client.getObjectMultiBulkReply();
}
