@Override public void initialize(final Bootstrap<JackhammerConfiguration> bootstrap){
  websocketBundle=new WebsocketBundle(SdkCommunicator.class);
  bootstrap.addBundle(guiceBundle);
  bootstrap.addBundle(websocketBundle);
}
