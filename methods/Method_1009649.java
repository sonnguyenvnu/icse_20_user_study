@Override public void open(Configuration parameters) throws Exception {
  receiverActorSystem=createDefaultActorSystem();
  if (configuration.hasPath("akka.remote.auto-ack") && configuration.getString("akka.remote.auto-ack").equals("on")) {
    autoAck=true;
  }
 else {
    autoAck=false;
  }
}
