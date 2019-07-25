@Override public void write(ByteBuf buf){
  writeVarInt(id,buf);
  if (data != null) {
    buf.writeBoolean(true);
    buf.writeBytes(data);
  }
 else {
    buf.writeBoolean(false);
  }
}
