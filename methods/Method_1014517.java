@Override public Status execute(Target src,int srcIndex,Status status){
  int source=(((cpu.getRegisterSet().getRegister("Y").getValue() & 0xFFFF) << 16)) | (cpu.getRegisterSet().getRegister("A").getValue() & 0xFFFF);
  if (src.get(srcIndex) == 0) {
    status.setBreakFlag(true);
    status.setErrorFlag(true);
  }
 else {
    cpu.getRegisterSet().getRegister("A").setValue((char)(source / (char)src.get(srcIndex)));
    cpu.getRegisterSet().getRegister("Y").setValue((char)(source % (char)src.get(srcIndex)));
  }
  return status;
}
