@Override public List<Cue> getCues(long timeUs){
  return root.getCues(timeUs,globalStyles,regionMap,imageMap);
}
