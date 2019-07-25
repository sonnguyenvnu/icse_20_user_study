@Override public void read(ByteBuf buf){
  entityId=buf.readInt();
  status=buf.readByte();
}
