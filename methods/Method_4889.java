/** 
 * Processes a new output format.
 */
private void processOutputFormat() throws ExoPlaybackException {
  MediaFormat format=codec.getOutputFormat();
  if (codecAdaptationWorkaroundMode != ADAPTATION_WORKAROUND_MODE_NEVER && format.getInteger(MediaFormat.KEY_WIDTH) == ADAPTATION_WORKAROUND_SLICE_WIDTH_HEIGHT && format.getInteger(MediaFormat.KEY_HEIGHT) == ADAPTATION_WORKAROUND_SLICE_WIDTH_HEIGHT) {
    shouldSkipAdaptationWorkaroundOutputBuffer=true;
    return;
  }
  if (codecNeedsMonoChannelCountWorkaround) {
    format.setInteger(MediaFormat.KEY_CHANNEL_COUNT,1);
  }
  onOutputFormatChanged(codec,format);
}
