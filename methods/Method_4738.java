@Override public void createTracks(ExtractorOutput extractorOutput,TrackIdGenerator idGenerator){
  idGenerator.generateNewId();
  formatId=idGenerator.getFormatId();
  output=extractorOutput.track(idGenerator.getTrackId(),C.TRACK_TYPE_AUDIO);
  if (exposeId3) {
    idGenerator.generateNewId();
    id3Output=extractorOutput.track(idGenerator.getTrackId(),C.TRACK_TYPE_METADATA);
    id3Output.format(Format.createSampleFormat(idGenerator.getFormatId(),MimeTypes.APPLICATION_ID3,null,Format.NO_VALUE,null));
  }
 else {
    id3Output=new DummyTrackOutput();
  }
}
