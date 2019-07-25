@Override public Status execute(int src,Status status){
  Register sp=cpu.getRegisterSet().getRegister("SP");
  sp.setValue(sp.getValue() - 1);
  cpu.getMemory().set(sp.getValue(),src);
  return status;
}
