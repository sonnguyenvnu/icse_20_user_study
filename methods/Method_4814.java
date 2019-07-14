private int readFirstPcrValue(ExtractorInput input,PositionHolder seekPositionHolder,int pcrPid) throws IOException, InterruptedException {
  int bytesToSearch=(int)Math.min(TIMESTAMP_SEARCH_BYTES,input.getLength());
  int searchStartPosition=0;
  if (input.getPosition() != searchStartPosition) {
    seekPositionHolder.position=searchStartPosition;
    return Extractor.RESULT_SEEK;
  }
  packetBuffer.reset(bytesToSearch);
  input.resetPeekPosition();
  input.peekFully(packetBuffer.data,0,bytesToSearch);
  firstPcrValue=readFirstPcrValueFromBuffer(packetBuffer,pcrPid);
  isFirstPcrValueRead=true;
  return Extractor.RESULT_CONTINUE;
}
