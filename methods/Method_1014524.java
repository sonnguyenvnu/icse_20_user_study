@Override public Status execute(int src,Status status){
  if (!status.isCarryFlag() && !status.isZeroFlag()) {
    cpu.setIp((char)src);
  }
  return status;
}
