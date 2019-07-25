@Override public Status execute(int src,Status status){
  cpu.setIp((char)src);
  return status;
}
