private void notifyFrameMetadataListener(long presentationTimeUs,long releaseTimeNs,Format format){
  if (frameMetadataListener != null) {
    frameMetadataListener.onVideoFrameAboutToBeRendered(presentationTimeUs,releaseTimeNs,format);
  }
}
