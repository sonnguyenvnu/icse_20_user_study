@Override public long getEventTime(int index){
  return subtitle.getEventTime(index) + subsampleOffsetUs;
}
