private int readLastPcrValue(ExtractorInput input,PositionHolder seekPositionHolder,int pcrPid) throws IOException, InterruptedException {
  long inputLength=input.getLength();
  int bytesToSearch=(int)Math.min(TIMESTAMP_SEARCH_BYTES,inputLength);
  long searchStartPosition=inputLength - bytesToSearch;
  if (input.getPosition() != searchStartPosition) {
    seekPositionHolder.position=searchStartPosition;
    return Extractor.RESULT_SEEK;
  }
  packetBuffer.reset(bytesToSearch);
  input.resetPeekPosition();
  input.peekFully(packetBuffer.data,0,bytesToSearch);
  lastPcrValue=readLastPcrValueFromBuffer(packetBuffer,pcrPid);
  isLastPcrValueRead=true;
  return Extractor.RESULT_CONTINUE;
}
