private void commitSubtitleSample(Track track,String timecodeFormat,int endTimecodeOffset,long lastTimecodeValueScalingFactor,byte[] emptyTimecode){
  setSampleDuration(subtitleSample.data,blockDurationUs,timecodeFormat,endTimecodeOffset,lastTimecodeValueScalingFactor,emptyTimecode);
  track.output.sampleData(subtitleSample,subtitleSample.limit());
  sampleBytesWritten+=subtitleSample.limit();
}
