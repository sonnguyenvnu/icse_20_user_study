@Override public void handle(PingPacket ping) throws Exception {
  Preconditions.checkState(thisState == State.PING,"Not expecting PING");
  unsafe.sendPacket(ping);
  disconnect("");
}
