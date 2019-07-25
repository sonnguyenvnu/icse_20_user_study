@Override public Status execute(int src,Status status){
  status.setErrorFlag(cpu.getHardwareHost().hardwareInterrupt(src,cpu.getStatus()));
  return status;
}
