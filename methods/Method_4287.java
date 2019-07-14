private void moveNewSamplesToPitchBuffer(int originalOutputFrameCount){
  int frameCount=outputFrameCount - originalOutputFrameCount;
  pitchBuffer=ensureSpaceForAdditionalFrames(pitchBuffer,pitchFrameCount,frameCount);
  System.arraycopy(outputBuffer,originalOutputFrameCount * channelCount,pitchBuffer,pitchFrameCount * channelCount,frameCount * channelCount);
  outputFrameCount=originalOutputFrameCount;
  pitchFrameCount+=frameCount;
}
