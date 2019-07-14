public void addSample(long offset,MediaCodec.BufferInfo bufferInfo){
  boolean isSyncFrame=!isAudio && (bufferInfo.flags & MediaCodec.BUFFER_FLAG_SYNC_FRAME) != 0;
  samples.add(new Sample(offset,bufferInfo.size));
  if (syncSamples != null && isSyncFrame) {
    syncSamples.add(samples.size());
  }
  samplePresentationTimes.add(new SamplePresentationTime(samplePresentationTimes.size(),(bufferInfo.presentationTimeUs * timeScale + 500000L) / 1000000L));
}
