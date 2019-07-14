@Override public List<Cue> getCues(long timeUs){
  return subtitle.getCues(timeUs - subsampleOffsetUs);
}
