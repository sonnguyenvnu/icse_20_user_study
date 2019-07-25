private void start(String props,String name,boolean nohup) throws Exception {
  channel=new JChannel(props);
  if (name != null)   channel.name(name);
  channel.setReceiver(this);
  channel.connect("ChatCluster");
  if (!nohup) {
    eventLoop();
    channel.close();
  }
}
