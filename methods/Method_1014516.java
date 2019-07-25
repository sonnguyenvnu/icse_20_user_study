@Override public Status execute(Target dst,int dstIndex,Status status){
  char a=(char)dst.get(dstIndex);
  int result=a - 1;
  status.setSignFlag(Util.checkSign16(result));
  status.setZeroFlag((char)result == 0);
  status.setOverflowFlag(Util.checkOverFlowSub16(a,1));
  dst.set(dstIndex,result);
  return status;
}
