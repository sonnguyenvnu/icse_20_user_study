public boolean stopThread(){
  if (thread == null) {
    return false;
  }
  thread=null;
  return true;
}
