@Override public long getNextLoadPositionUs(){
  return enabledTrackCount == 0 ? C.TIME_END_OF_SOURCE : getBufferedPositionUs();
}
