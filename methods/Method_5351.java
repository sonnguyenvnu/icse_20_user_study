private DefaultExtractorInput prepareExtraction(DataSource dataSource,DataSpec dataSpec) throws IOException, InterruptedException {
  long bytesToRead=dataSource.open(dataSpec);
  DefaultExtractorInput extractorInput=new DefaultExtractorInput(dataSource,dataSpec.absoluteStreamPosition,bytesToRead);
  if (extractor == null) {
    long id3Timestamp=peekId3PrivTimestamp(extractorInput);
    extractorInput.resetPeekPosition();
    Pair<Extractor,Boolean> extractorData=extractorFactory.createExtractor(previousExtractor,dataSpec.uri,trackFormat,muxedCaptionFormats,drmInitData,timestampAdjuster,dataSource.getResponseHeaders(),extractorInput);
    extractor=extractorData.first;
    boolean reusingExtractor=extractor == previousExtractor;
    boolean isPackedAudioExtractor=extractorData.second;
    if (isPackedAudioExtractor) {
      output.setSampleOffsetUs(id3Timestamp != C.TIME_UNSET ? timestampAdjuster.adjustTsTimestamp(id3Timestamp) : startTimeUs);
    }
    initLoadCompleted=reusingExtractor && initDataSpec != null;
    output.init(uid,shouldSpliceIn,reusingExtractor);
    if (!reusingExtractor) {
      extractor.init(output);
    }
  }
  return extractorInput;
}
