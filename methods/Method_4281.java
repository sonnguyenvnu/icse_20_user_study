private void copyToOutput(short[] samples,int positionFrames,int frameCount){
  outputBuffer=ensureSpaceForAdditionalFrames(outputBuffer,outputFrameCount,frameCount);
  System.arraycopy(samples,positionFrames * channelCount,outputBuffer,outputFrameCount * channelCount,frameCount * channelCount);
  outputFrameCount+=frameCount;
}
