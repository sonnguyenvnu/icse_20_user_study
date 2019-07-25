@Override public void handle(PacketWrapper packet) throws Exception {
  if (packet.packet == null) {
    throw new QuietException("Unexpected packet received during server login process!\n" + BufUtil.dump(packet.buf,16));
  }
}
