private void removePitchFrames(int frameCount){
  if (frameCount == 0) {
    return;
  }
  System.arraycopy(pitchBuffer,frameCount * channelCount,pitchBuffer,0,(pitchFrameCount - frameCount) * channelCount);
  pitchFrameCount-=frameCount;
}
