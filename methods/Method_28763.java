@Override public String clusterAddSlots(final int... slots){
  checkIsInMultiOrPipeline();
  client.clusterAddSlots(slots);
  return client.getStatusCodeReply();
}
