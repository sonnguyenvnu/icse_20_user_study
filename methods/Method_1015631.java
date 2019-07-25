public void disconnect(String group,Address addr) throws Exception {
  writeRequest(new GossipData(GossipType.UNREGISTER,group,addr));
}
