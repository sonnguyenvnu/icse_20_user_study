public void write(int var){
  assert var < nLocals : "local var num=" + var + " exceeds nLocals = " + nLocals;
  def.set(var);
}
