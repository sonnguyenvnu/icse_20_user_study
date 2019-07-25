@Override public void read(ByteBuf buf){
  position=buf.readByte();
  name=readString(buf);
}
