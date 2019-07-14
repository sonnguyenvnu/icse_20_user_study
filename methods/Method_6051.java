/** 
 * Called when a buffer was processed in tunneling mode. 
 */
protected void onProcessedTunneledBuffer(long presentationTimeUs){
  @Nullable Format format=updateOutputFormatForTime(presentationTimeUs);
  if (format != null) {
    processOutputFormat(getCodec(),format.width,format.height);
  }
  maybeNotifyVideoSizeChanged();
  maybeNotifyRenderedFirstFrame();
  onProcessedOutputBuffer(presentationTimeUs);
}
