public void read(int var){
  assert var < nLocals : "local var num=" + var + " exceeds nLocals = " + nLocals;
  if (!def.get(var)) {
    use.set(var);
  }
}
