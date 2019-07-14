public void zrevrangeByScore(final byte[] key,final String max,final String min,final int offset,int count){
  sendCommand(ZREVRANGEBYSCORE,key,max.getBytes(),min.getBytes(),LIMIT.raw,toByteArray(offset),toByteArray(count));
}
