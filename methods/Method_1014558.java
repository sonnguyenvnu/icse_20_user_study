@Override public Status execute(Status status){
  Register sp=cpu.getRegisterSet().getRegister("SP");
  char flags=(char)cpu.getMemory().get(sp.getValue());
  status.fromByte(flags);
  sp.setValue(sp.getValue() + 1);
  return status;
}
