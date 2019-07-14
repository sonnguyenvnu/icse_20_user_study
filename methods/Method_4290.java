private void adjustRate(float rate,int originalOutputFrameCount){
  if (outputFrameCount == originalOutputFrameCount) {
    return;
  }
  int newSampleRate=(int)(inputSampleRateHz / rate);
  int oldSampleRate=inputSampleRateHz;
  while (newSampleRate > (1 << 14) || oldSampleRate > (1 << 14)) {
    newSampleRate/=2;
    oldSampleRate/=2;
  }
  moveNewSamplesToPitchBuffer(originalOutputFrameCount);
  for (int position=0; position < pitchFrameCount - 1; position++) {
    while ((oldRatePosition + 1) * newSampleRate > newRatePosition * oldSampleRate) {
      outputBuffer=ensureSpaceForAdditionalFrames(outputBuffer,outputFrameCount,1);
      for (int i=0; i < channelCount; i++) {
        outputBuffer[outputFrameCount * channelCount + i]=interpolate(pitchBuffer,position * channelCount + i,oldSampleRate,newSampleRate);
      }
      newRatePosition++;
      outputFrameCount++;
    }
    oldRatePosition++;
    if (oldRatePosition == oldSampleRate) {
      oldRatePosition=0;
      Assertions.checkState(newRatePosition == newSampleRate);
      newRatePosition=0;
    }
  }
  removePitchFrames(pitchFrameCount - 1);
}
