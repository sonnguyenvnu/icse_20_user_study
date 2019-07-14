@Override public List<String> clusterGetKeysInSlot(final int slot,final int count){
  checkIsInMultiOrPipeline();
  client.clusterGetKeysInSlot(slot,count);
  return client.getMultiBulkReply();
}
