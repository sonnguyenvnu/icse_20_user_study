/** 
 * Forces generating output using whatever data has been queued already. No extra delay will be added to the output, but flushing in the middle of words could introduce distortion.
 */
public void queueEndOfStream(){
  int remainingFrameCount=inputFrameCount;
  float s=speed / pitch;
  float r=rate * pitch;
  int expectedOutputFrames=outputFrameCount + (int)((remainingFrameCount / s + pitchFrameCount) / r + 0.5f);
  inputBuffer=ensureSpaceForAdditionalFrames(inputBuffer,inputFrameCount,remainingFrameCount + 2 * maxRequiredFrameCount);
  for (int xSample=0; xSample < 2 * maxRequiredFrameCount * channelCount; xSample++) {
    inputBuffer[remainingFrameCount * channelCount + xSample]=0;
  }
  inputFrameCount+=2 * maxRequiredFrameCount;
  processStreamInput();
  if (outputFrameCount > expectedOutputFrames) {
    outputFrameCount=expectedOutputFrames;
  }
  inputFrameCount=0;
  remainingInputToCopyFrameCount=0;
  pitchFrameCount=0;
}
