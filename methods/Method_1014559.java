@Override public Status execute(Target dst,int dstIndex,Status status){
  dst.set(dstIndex,cpu.getMemory().get(cpu.getRegisterSet().getRegister("SP").getValue()));
  cpu.getRegisterSet().getRegister("SP").setValue(cpu.getRegisterSet().getRegister("SP").getValue() + 1);
  return status;
}
