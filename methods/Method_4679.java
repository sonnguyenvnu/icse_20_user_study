@Override public OggSeekMap createSeekMap(){
  return totalGranules != 0 ? new OggSeekMap() : null;
}
