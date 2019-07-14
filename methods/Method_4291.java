private int skipPitchPeriod(short[] samples,int position,float speed,int period){
  int newFrameCount;
  if (speed >= 2.0f) {
    newFrameCount=(int)(period / (speed - 1.0f));
  }
 else {
    newFrameCount=period;
    remainingInputToCopyFrameCount=(int)(period * (2.0f - speed) / (speed - 1.0f));
  }
  outputBuffer=ensureSpaceForAdditionalFrames(outputBuffer,outputFrameCount,newFrameCount);
  overlapAdd(newFrameCount,channelCount,outputBuffer,outputFrameCount,samples,position,samples,position + period);
  outputFrameCount+=newFrameCount;
  return newFrameCount;
}
