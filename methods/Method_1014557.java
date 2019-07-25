@Override public Status execute(Target dst,int dstIndex,int src,Status status){
  int a=(char)dst.get(dstIndex);
  int b=(char)src;
  int result=(a | b);
  status.setSignFlag(Util.checkSign16(result));
  status.setZeroFlag((char)result == 0);
  status.setOverflowFlag(false);
  status.setCarryFlag(false);
  dst.set(dstIndex,result);
  return status;
}
