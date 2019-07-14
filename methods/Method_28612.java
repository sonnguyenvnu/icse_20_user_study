public void zrevrangeByScore(final byte[] key,final byte[] max,final byte[] min,final int offset,int count){
  sendCommand(ZREVRANGEBYSCORE,key,max,min,LIMIT.raw,toByteArray(offset),toByteArray(count));
}
