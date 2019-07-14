@Override public void createTracks(ExtractorOutput extractorOutput,TrackIdGenerator generator){
  generator.generateNewId();
  trackFormatId=generator.getFormatId();
  output=extractorOutput.track(generator.getTrackId(),C.TRACK_TYPE_AUDIO);
}
