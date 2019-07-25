@Override public Status execute(int src,Status status){
  int result=cpu.getRegisterSet().getRegister("A").getValue() * (char)src;
  int hWord=Util.getHigherWord(result);
  if (hWord != 0) {
    status.setOverflowFlag(true);
    status.setCarryFlag(true);
    cpu.getRegisterSet().getRegister("Y").setValue(hWord);
  }
 else {
    status.setOverflowFlag(false);
    status.setCarryFlag(false);
  }
  cpu.getRegisterSet().getRegister("A").setValue(Util.getLowerWord(result));
  return status;
}
