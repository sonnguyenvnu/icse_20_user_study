@Override public void write(ByteBuf buf){
  writeString(uuid,buf);
  writeString(username,buf);
}
