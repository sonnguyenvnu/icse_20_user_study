@Override public int getPagePerSecond(){
  int runSeconds=(int)(System.currentTimeMillis() - getStartTime().getTime()) / 1000;
  return getSuccessPageCount() / runSeconds;
}
