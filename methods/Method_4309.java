@Override public final int getBufferedPercentage(){
  long position=getBufferedPosition();
  long duration=getDuration();
  return position == C.TIME_UNSET || duration == C.TIME_UNSET ? 0 : duration == 0 ? 100 : Util.constrainValue((int)((position * 100) / duration),0,100);
}
