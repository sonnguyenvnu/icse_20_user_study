private int insertPitchPeriod(short[] samples,int position,float speed,int period){
  int newFrameCount;
  if (speed < 0.5f) {
    newFrameCount=(int)(period * speed / (1.0f - speed));
  }
 else {
    newFrameCount=period;
    remainingInputToCopyFrameCount=(int)(period * (2.0f * speed - 1.0f) / (1.0f - speed));
  }
  outputBuffer=ensureSpaceForAdditionalFrames(outputBuffer,outputFrameCount,period + newFrameCount);
  System.arraycopy(samples,position * channelCount,outputBuffer,outputFrameCount * channelCount,period * channelCount);
  overlapAdd(newFrameCount,channelCount,outputBuffer,outputFrameCount + period,samples,position + period,samples,position);
  outputFrameCount+=period + newFrameCount;
  return newFrameCount;
}
