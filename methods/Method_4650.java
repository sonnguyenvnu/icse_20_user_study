@Override public void seek(long position,long timeUs){
  containerAtoms.clear();
  atomHeaderBytesRead=0;
  sampleTrackIndex=C.INDEX_UNSET;
  sampleBytesWritten=0;
  sampleCurrentNalBytesRemaining=0;
  if (position == 0) {
    enterReadingAtomHeaderState();
  }
 else   if (tracks != null) {
    updateSampleIndices(timeUs);
  }
}
