@Override public long seekToUs(long positionUs){
  for (int i=0; i < sampleStreams.size(); i++) {
    sampleStreams.get(i).reset();
  }
  return positionUs;
}
