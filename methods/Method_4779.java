@Override public void createTracks(ExtractorOutput extractorOutput,TrackIdGenerator idGenerator){
  idGenerator.generateNewId();
  output=extractorOutput.track(idGenerator.getTrackId(),C.TRACK_TYPE_AUDIO);
  formatId=idGenerator.getFormatId();
}
