@Override public void read(ByteBuf buf){
  distance=DefinedPacket.readVarInt(buf);
}
