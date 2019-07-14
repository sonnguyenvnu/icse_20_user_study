@Override public int getNextEventTimeIndex(long timeUs){
  int index=Util.binarySearchCeil(cueTimesUs,timeUs,false,false);
  return index < cueTimesUs.length ? index : C.INDEX_UNSET;
}
