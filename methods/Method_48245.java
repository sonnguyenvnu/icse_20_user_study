private StaticBuffer getBlockApplication(long blockValue,Instant timestamp){
  WriteByteBuffer bb=new WriteByteBuffer(8 + 8 + uidBytes.length);
  bb.putLong(-blockValue).putLong(times.getTime(timestamp));
  WriteBufferUtil.put(bb,uidBytes);
  return bb.getStaticBuffer();
}
