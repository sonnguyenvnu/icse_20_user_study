@Override public void write(ByteBuf buf){
  writeVarInt(id,buf);
  writeString(channel,buf);
  buf.writeBytes(data);
}
