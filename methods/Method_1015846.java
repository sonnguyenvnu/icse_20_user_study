@Override public void write(ByteBuf buf){
  buf.writeInt(entityId);
  buf.writeByte(status);
}
