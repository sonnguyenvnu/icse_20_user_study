@Override public Status execute(Target dst,int dstIndex,Target src,int srcIndex,Status status){
  int a=(char)dst.get(dstIndex);
  int count=(char)src.get(srcIndex);
  int result=a >> count;
  if (Util.checkSign16(a) != Util.checkSign16(result)) {
    status.setOverflowFlag(true);
  }
  status.setCarryFlag((((a << 16) >> count) & 0x8000) != 0);
  status.setSignFlag(Util.checkSign16(result));
  status.setZeroFlag((char)result == 0);
  dst.set(dstIndex,result);
  return status;
}
