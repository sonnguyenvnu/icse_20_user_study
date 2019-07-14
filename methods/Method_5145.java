private void maybeNotifyPrimaryTrackFormatChanged(){
  int readSampleIndex=primarySampleQueue.getReadIndex();
  int notifyToMediaChunkIndex=primarySampleIndexToMediaChunkIndex(readSampleIndex,nextNotifyPrimaryFormatMediaChunkIndex - 1);
  while (nextNotifyPrimaryFormatMediaChunkIndex <= notifyToMediaChunkIndex) {
    maybeNotifyPrimaryTrackFormatChanged(nextNotifyPrimaryFormatMediaChunkIndex++);
  }
}
