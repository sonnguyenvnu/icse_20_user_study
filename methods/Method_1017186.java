@Override public ClientWrapper setup() throws Exception {
  final Settings settings=Settings.builder().put("path.home",root).put("node.name",InetAddress.getLocalHost().getHostName()).put("script.inline","on").put("cluster.name",clusterName).put("transport.type","local").put("http.enabled",false).build();
  final Node node=new Node(settings).start();
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
