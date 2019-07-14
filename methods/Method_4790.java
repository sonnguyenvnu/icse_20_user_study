@Override public void createTracks(ExtractorOutput extractorOutput,TrackIdGenerator idGenerator){
  idGenerator.generateNewId();
  formatId=idGenerator.getFormatId();
  output=extractorOutput.track(idGenerator.getTrackId(),C.TRACK_TYPE_AUDIO);
}
