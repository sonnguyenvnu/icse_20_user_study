/** 
 * Peeks the Adts header of the current frame and checks if it is valid. If the header is valid, transition to  {@link #STATE_READING_ADTS_HEADER}; else, transition to  {@link #STATE_FINDING_SAMPLE}.
 */
private void checkAdtsHeader(ParsableByteArray buffer){
  if (buffer.bytesLeft() == 0) {
    return;
  }
  adtsScratch.data[0]=buffer.data[buffer.getPosition()];
  adtsScratch.setPosition(2);
  int currentFrameSampleRateIndex=adtsScratch.readBits(4);
  if (firstFrameSampleRateIndex != C.INDEX_UNSET && currentFrameSampleRateIndex != firstFrameSampleRateIndex) {
    resetSync();
    return;
  }
  if (!foundFirstFrame) {
    foundFirstFrame=true;
    firstFrameVersion=currentFrameVersion;
    firstFrameSampleRateIndex=currentFrameSampleRateIndex;
  }
  setReadingAdtsHeaderState();
}
