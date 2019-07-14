@Override public void createTracks(ExtractorOutput extractorOutput,TrackIdGenerator idGenerator){
  for (int i=0; i < outputs.length; i++) {
    DvbSubtitleInfo subtitleInfo=subtitleInfos.get(i);
    idGenerator.generateNewId();
    TrackOutput output=extractorOutput.track(idGenerator.getTrackId(),C.TRACK_TYPE_TEXT);
    output.format(Format.createImageSampleFormat(idGenerator.getFormatId(),MimeTypes.APPLICATION_DVBSUBS,null,Format.NO_VALUE,0,Collections.singletonList(subtitleInfo.initializationData),subtitleInfo.language,null));
    outputs[i]=output;
  }
}
