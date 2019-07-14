@Override public void consume(ParsableByteArray sectionData){
  if (!formatDeclared) {
    if (timestampAdjuster.getTimestampOffsetUs() == C.TIME_UNSET) {
      return;
    }
    output.format(Format.createSampleFormat(null,MimeTypes.APPLICATION_SCTE35,timestampAdjuster.getTimestampOffsetUs()));
    formatDeclared=true;
  }
  int sampleSize=sectionData.bytesLeft();
  output.sampleData(sectionData,sampleSize);
  output.sampleMetadata(timestampAdjuster.getLastAdjustedTimestampUs(),C.BUFFER_FLAG_KEY_FRAME,sampleSize,0,null);
}
