private void changeSpeed(float speed){
  if (inputFrameCount < maxRequiredFrameCount) {
    return;
  }
  int frameCount=inputFrameCount;
  int positionFrames=0;
  do {
    if (remainingInputToCopyFrameCount > 0) {
      positionFrames+=copyInputToOutput(positionFrames);
    }
 else {
      int period=findPitchPeriod(inputBuffer,positionFrames);
      if (speed > 1.0) {
        positionFrames+=period + skipPitchPeriod(inputBuffer,positionFrames,speed,period);
      }
 else {
        positionFrames+=insertPitchPeriod(inputBuffer,positionFrames,speed,period);
      }
    }
  }
 while (positionFrames + maxRequiredFrameCount <= frameCount);
  removeProcessedInputFrames(positionFrames);
}
