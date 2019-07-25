@Override public Status execute(int src,Status status){
  if (status.isSignFlag()) {
    cpu.setIp((char)src);
  }
  return status;
}
