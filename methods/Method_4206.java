private long applySkipping(long positionUs){
  return positionUs + framesToDurationUs(audioProcessorChain.getSkippedOutputFrameCount());
}
