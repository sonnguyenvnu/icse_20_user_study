@Override public Status execute(Target src,int srcIndex,Status status){
  if (!status.isCarryFlag() && !status.isZeroFlag()) {
    cpu.setIp((char)src.get(srcIndex));
  }
  return status;
}
