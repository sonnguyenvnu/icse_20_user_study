@Override public Status execute(Status status){
  cpu.setIp((char)cpu.getMemory().get(cpu.getRegisterSet().get(7)));
  cpu.getRegisterSet().set(7,cpu.getRegisterSet().get(7) + 1);
  return status;
}
