public Map<String,String> pubsubNumSub(String... channels){
  checkIsInMultiOrPipeline();
  client.pubsubNumSub(channels);
  return BuilderFactory.PUBSUB_NUMSUB_MAP.build(client.getBinaryMultiBulkReply());
}
