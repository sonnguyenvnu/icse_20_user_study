@Override public boolean isEnded(){
  return inputEnded && (sonic == null || sonic.getFramesAvailable() == 0);
}
