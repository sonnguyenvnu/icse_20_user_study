private void removeProcessedInputFrames(int positionFrames){
  int remainingFrames=inputFrameCount - positionFrames;
  System.arraycopy(inputBuffer,positionFrames * channelCount,inputBuffer,0,remainingFrames * channelCount);
  inputFrameCount=remainingFrames;
}
