@Override public int getNextEventTimeIndex(long timeUs){
  int index=Util.binarySearchCeil(eventTimesUs,timeUs,false,false);
  return index < eventTimesUs.length ? index : C.INDEX_UNSET;
}
