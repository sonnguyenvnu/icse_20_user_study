@Override public Status execute(Target src,int srcIndex,Status status){
  if (status.isSignFlag()) {
    cpu.setIp((char)src.get(srcIndex));
  }
  return status;
}
