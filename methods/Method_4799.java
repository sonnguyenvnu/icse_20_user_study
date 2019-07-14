@Override public void init(TimestampAdjuster timestampAdjuster,ExtractorOutput extractorOutput,TrackIdGenerator idGenerator){
  this.timestampAdjuster=timestampAdjuster;
  reader.createTracks(extractorOutput,idGenerator);
}
