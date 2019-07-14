@Override public String info(final String section){
  client.info(section);
  return client.getBulkReply();
}
