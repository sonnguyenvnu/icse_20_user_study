@Override public Status execute(int src,Status status){
  if (!status.isCarryFlag()) {
    cpu.setIp((char)src);
  }
  return status;
}
