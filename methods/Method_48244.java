private StaticBuffer[] getBlockSlice(long blockValue){
  StaticBuffer[] slice=new StaticBuffer[2];
  slice[0]=new WriteByteBuffer(16).putLong(-blockValue).putLong(0).getStaticBuffer();
  slice[1]=new WriteByteBuffer(16).putLong(-blockValue).putLong(-1).getStaticBuffer();
  return slice;
}
