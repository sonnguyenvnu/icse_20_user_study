@Override public void write(ByteBuf buf){
  buf.writeByte(position);
  writeString(name,buf);
}
