@Override public Status execute(Target src,int srcIndex,Status status){
  cpu.setIp((char)src.get(srcIndex));
  return status;
}
