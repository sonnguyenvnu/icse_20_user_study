@Override public void init(TimestampAdjuster timestampAdjuster,ExtractorOutput extractorOutput,TsPayloadReader.TrackIdGenerator idGenerator){
  this.timestampAdjuster=timestampAdjuster;
  idGenerator.generateNewId();
  output=extractorOutput.track(idGenerator.getTrackId(),C.TRACK_TYPE_METADATA);
  output.format(Format.createSampleFormat(idGenerator.getFormatId(),MimeTypes.APPLICATION_SCTE35,null,Format.NO_VALUE,null));
}
