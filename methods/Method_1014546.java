@Override public Status execute(int src,Status status){
  if (status.isOverflowFlag()) {
    cpu.setIp((char)src);
  }
  return status;
}
