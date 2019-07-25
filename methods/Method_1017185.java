@Override public ClientWrapper setup() throws Exception {
  final Settings settings=Settings.builder().put("node.name",InetAddress.getLocalHost().getHostName()).putArray("discovery.zen.ping.unicast.hosts",seeds).put("cluster.name",clusterName).put("node.data",false).build();
  final Node node=new Node(settings);
  return new ClientWrapper(node.client(),new Runnable(){
    @Override public void run(){
      try {
        node.close();
      }
 catch (      IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
);
}
