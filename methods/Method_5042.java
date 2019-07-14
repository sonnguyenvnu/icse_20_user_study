@Override public final float getDownloadPercentage(){
  long totalBytes=this.totalBytes;
  if (totalBytes != C.LENGTH_UNSET) {
    return totalBytes == 0 ? 100f : (downloadedBytes * 100f) / totalBytes;
  }
  int totalSegments=this.totalSegments;
  int downloadedSegments=this.downloadedSegments;
  if (totalSegments == C.LENGTH_UNSET || downloadedSegments == C.LENGTH_UNSET) {
    return C.PERCENTAGE_UNSET;
  }
  return totalSegments == 0 ? 100f : (downloadedSegments * 100f) / totalSegments;
}
