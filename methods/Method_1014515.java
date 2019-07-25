@Override public Status execute(Target dst,int dstIndex,Target src,int srcIndex,Status status){
  int a=(char)dst.get(dstIndex);
  int b=(char)src.get(srcIndex);
  int result=a - b;
  status.setSignFlag(Util.checkSign16(result));
  status.setZeroFlag((char)result == 0);
  status.setOverflowFlag(Util.checkOverFlowSub16(a,b));
  status.setCarryFlag(Util.checkCarry16(result));
  return status;
}
