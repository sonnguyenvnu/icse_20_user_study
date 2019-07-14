public void createTracks(ExtractorOutput extractorOutput,TrackIdGenerator idGenerator){
  for (int i=0; i < outputs.length; i++) {
    idGenerator.generateNewId();
    TrackOutput output=extractorOutput.track(idGenerator.getTrackId(),C.TRACK_TYPE_TEXT);
    Format channelFormat=closedCaptionFormats.get(i);
    String channelMimeType=channelFormat.sampleMimeType;
    Assertions.checkArgument(MimeTypes.APPLICATION_CEA608.equals(channelMimeType) || MimeTypes.APPLICATION_CEA708.equals(channelMimeType),"Invalid closed caption mime type provided: " + channelMimeType);
    String formatId=channelFormat.id != null ? channelFormat.id : idGenerator.getFormatId();
    output.format(Format.createTextSampleFormat(formatId,channelMimeType,null,Format.NO_VALUE,channelFormat.selectionFlags,channelFormat.language,channelFormat.accessibilityChannel,null,Format.OFFSET_SAMPLE_RELATIVE,channelFormat.initializationData));
    outputs[i]=output;
  }
}
