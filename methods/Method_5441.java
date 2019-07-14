@Override public long getAdjustedSeekPositionUs(long positionUs,SeekParameters seekParameters){
  return enabledPeriods[0].getAdjustedSeekPositionUs(positionUs,seekParameters);
}
