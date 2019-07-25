public boolean QUIT(){
  if (!notConnected()) {
    exec("close",false);
  }
  return false;
}
