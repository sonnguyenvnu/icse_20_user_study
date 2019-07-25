public void born(int var){
  assert var < nLocals : "local var num=" + var + " exceeds nLocals = " + nLocals;
  born.set(var);
}
