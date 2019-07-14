public StaticBuffer toLockKey(StaticBuffer key,StaticBuffer column){
  WriteBuffer b=new WriteByteBuffer(key.length() + column.length() + 4);
  b.putInt(key.length());
  WriteBufferUtil.put(b,key);
  WriteBufferUtil.put(b,column);
  return b.getStaticBuffer();
}
