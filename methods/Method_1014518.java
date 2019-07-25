@Override public Status execute(Target src,int srcIndex,Status status){
  status.setErrorFlag(cpu.getHardwareHost().hardwareInterrupt(src.get(srcIndex),cpu.getStatus()));
  return status;
}
