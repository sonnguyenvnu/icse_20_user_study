@Override public void init(HmInterface hmInterface,String clientId) throws IOException {
  super.init(hmInterface,clientId);
  socketHandler.removeSocket(config.getRpcPort(hmInterface));
}
