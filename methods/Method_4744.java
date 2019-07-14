/** 
 * Returns whether the given syncPositionCandidate is a real SYNC word. <p>SYNC word pattern can occur within AAC data, so we perform a few checks to make sure this is really a SYNC word. This includes: <ul> <li>Checking if MPEG version of this frame matches the first detected version. <li>Checking if the sample rate index of this frame matches the first detected sample rate index. <li>Checking if the bytes immediately after the current package also match a SYNC-word. </ul> If the buffer runs out of data for any check, optimistically skip that check, because AdtsReader consumes each buffer as a whole. We will still run a header validity check later.
 */
private boolean checkSyncPositionValid(ParsableByteArray pesBuffer,int syncPositionCandidate){
  pesBuffer.setPosition(syncPositionCandidate + 1);
  if (!tryRead(pesBuffer,adtsScratch.data,1)) {
    return false;
  }
  adtsScratch.setPosition(4);
  int currentFrameVersion=adtsScratch.readBits(1);
  if (firstFrameVersion != VERSION_UNSET && currentFrameVersion != firstFrameVersion) {
    return false;
  }
  if (firstFrameSampleRateIndex != C.INDEX_UNSET) {
    if (!tryRead(pesBuffer,adtsScratch.data,1)) {
      return true;
    }
    adtsScratch.setPosition(2);
    int currentFrameSampleRateIndex=adtsScratch.readBits(4);
    if (currentFrameSampleRateIndex != firstFrameSampleRateIndex) {
      return false;
    }
    pesBuffer.setPosition(syncPositionCandidate + 2);
  }
  if (!tryRead(pesBuffer,adtsScratch.data,4)) {
    return true;
  }
  adtsScratch.setPosition(14);
  int frameSize=adtsScratch.readBits(13);
  if (frameSize <= 6) {
    return false;
  }
  int nextSyncPosition=syncPositionCandidate + frameSize;
  if (nextSyncPosition + 1 >= pesBuffer.limit()) {
    return true;
  }
  return (isAdtsSyncBytes(pesBuffer.data[nextSyncPosition],pesBuffer.data[nextSyncPosition + 1]) && (firstFrameVersion == VERSION_UNSET || ((pesBuffer.data[nextSyncPosition + 1] & 0x8) >> 3) == currentFrameVersion));
}
