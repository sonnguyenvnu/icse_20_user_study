@Override public Status execute(Target src,int srcIndex,Status status){
  b.setValue(cpu.getHardwareHost().hardwareQuery(src.get(srcIndex)));
  return status;
}
