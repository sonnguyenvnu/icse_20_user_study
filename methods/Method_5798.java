private void maybeNotifyBandwidthSample(int elapsedMs,long bytesTransferred,long bitrateEstimate){
  if (elapsedMs == 0 && bytesTransferred == 0 && bitrateEstimate == lastReportedBitrateEstimate) {
    return;
  }
  lastReportedBitrateEstimate=bitrateEstimate;
  eventDispatcher.dispatch(listener -> listener.onBandwidthSample(elapsedMs,bytesTransferred,bitrateEstimate));
}
