@Override public int getNextEventTimeIndex(long timeUs){
  return timeUs < 0 ? 0 : C.INDEX_UNSET;
}
