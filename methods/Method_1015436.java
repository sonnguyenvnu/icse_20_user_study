/** 
 * Method called from other app, injecting channel 
 */
public void start(JChannel ch) throws Exception {
  channel=ch;
  channel.setReceiver(this);
  channel.connect("ChatCluster");
  eventLoop();
  channel.close();
}
