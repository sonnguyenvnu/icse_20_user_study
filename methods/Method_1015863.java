@Override public Title reset(){
  if (reset == null) {
    reset=createPacket(Action.RESET);
  }
  title=null;
  subtitle=null;
  times=null;
  return this;
}
