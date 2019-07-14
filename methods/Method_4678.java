@Override public long startSeek(long timeUs){
  Assertions.checkArgument(state == STATE_IDLE || state == STATE_SEEK);
  targetGranule=timeUs == 0 ? 0 : streamReader.convertTimeToGranule(timeUs);
  state=STATE_SEEK;
  resetSeeking();
  return targetGranule;
}
