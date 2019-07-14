private SeekMap getConstantBitrateSeekMap(long inputLength){
  int bitrate=getBitrateFromFrameSize(firstSampleSize,SAMPLE_TIME_PER_FRAME_US);
  return new ConstantBitrateSeekMap(inputLength,firstSamplePosition,bitrate,firstSampleSize);
}
