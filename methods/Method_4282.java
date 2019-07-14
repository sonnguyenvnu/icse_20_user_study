private int copyInputToOutput(int positionFrames){
  int frameCount=Math.min(maxRequiredFrameCount,remainingInputToCopyFrameCount);
  copyToOutput(inputBuffer,positionFrames,frameCount);
  remainingInputToCopyFrameCount-=frameCount;
  return frameCount;
}
