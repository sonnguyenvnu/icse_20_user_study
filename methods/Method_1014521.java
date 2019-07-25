@Override public Status execute(int src,Status status){
  b.setValue(cpu.getHardwareHost().hardwareQuery(src));
  return status;
}
