@Override public Status execute(Target src,int srcIndex,Status status){
  Register sp=cpu.getRegisterSet().getRegister("SP");
  sp.setValue(sp.getValue() - 1);
  cpu.getMemory().set(sp.getValue(),src.get(srcIndex));
  return status;
}
