private void connect(){
  fastForwardNodes.forEach(node -> {
    InetAddress address=new InetSocketAddress(node.getHost(),node.getPort()).getAddress();
    channelManager.getActiveNodes().put(address,node);
  }
);
}
