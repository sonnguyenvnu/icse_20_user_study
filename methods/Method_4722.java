@Override public void init(ExtractorOutput output){
  output.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
  trackOutput=output.track(0,C.TRACK_TYPE_TEXT);
  output.endTracks();
  trackOutput.format(format);
}
