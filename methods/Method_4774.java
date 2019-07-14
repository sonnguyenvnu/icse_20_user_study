@Override public void createTracks(ExtractorOutput extractorOutput,TrackIdGenerator idGenerator){
  idGenerator.generateNewId();
  output=extractorOutput.track(idGenerator.getTrackId(),C.TRACK_TYPE_METADATA);
  output.format(Format.createSampleFormat(idGenerator.getFormatId(),MimeTypes.APPLICATION_ID3,null,Format.NO_VALUE,null));
}
