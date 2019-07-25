@Override public Status execute(Target dst,int dstIndex,Status status){
  char destination=(char)dst.get(dstIndex);
  if (destination == 0) {
    status.setCarryFlag(false);
    status.setZeroFlag(true);
  }
 else {
    status.setCarryFlag(true);
  }
  if (destination == 0x8000) {
    status.setOverflowFlag(true);
  }
 else {
    dst.set(dstIndex,-destination);
  }
  return status;
}
