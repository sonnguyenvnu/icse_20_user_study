@Override public Status execute(Status status){
  Register sp=cpu.getRegisterSet().getRegister("SP");
  sp.setValue(sp.getValue() - 1);
  cpu.getMemory().set(sp.getValue(),status.toByte());
  return status;
}
