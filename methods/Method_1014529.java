@Override public Status execute(int src,Status status){
  if (status.isSignFlag() == status.isOverflowFlag() && !status.isZeroFlag()) {
    cpu.setIp((char)src);
  }
  return status;
}
