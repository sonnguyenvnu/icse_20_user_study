@Override public List<Cue> getCues(long timeUs){
  int index=Util.binarySearchFloor(cueTimesUs,timeUs,true,false);
  if (index == -1 || cues[index] == null) {
    return Collections.emptyList();
  }
 else {
    return Collections.singletonList(cues[index]);
  }
}
