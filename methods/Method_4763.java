@Override public void createTracks(ExtractorOutput extractorOutput,TrackIdGenerator idGenerator){
  idGenerator.generateNewId();
  formatId=idGenerator.getFormatId();
  output=extractorOutput.track(idGenerator.getTrackId(),C.TRACK_TYPE_VIDEO);
  sampleReader=new SampleReader(output,allowNonIdrKeyframes,detectAccessUnits);
  seiReader.createTracks(extractorOutput,idGenerator);
}
