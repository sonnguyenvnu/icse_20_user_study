private SeekMap getConstantBitrateSeekMap(long inputLength){
  int bitrate=getBitrateFromFrameSize(averageFrameSize,reader.getSampleDurationUs());
  return new ConstantBitrateSeekMap(inputLength,firstFramePosition,bitrate,averageFrameSize);
}
