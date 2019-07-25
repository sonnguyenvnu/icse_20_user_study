@Override public Status execute(Target src,int srcIndex,Status status){
  cpu.getRegisterSet().set(7,cpu.getRegisterSet().get(7) - 1);
  cpu.getMemory().set(cpu.getRegisterSet().get(7),cpu.getIp());
  cpu.setIp((char)src.get(srcIndex));
  return status;
}
