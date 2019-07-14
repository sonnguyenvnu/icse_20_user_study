/** 
 * Sets track selection parameters used during the start-up phase before the selection can be made purely on based on buffer size. During the start-up phase the selection is based on the current bandwidth estimate.
 * @param bandwidthFraction The fraction of the available bandwidth that the selection shouldconsider available for use. Setting to a value less than 1 is recommended to account for inaccuracies in the bandwidth estimator.
 * @param minBufferForQualityIncreaseMs The minimum duration of buffered media required for theselected track to switch to one of higher quality.
 * @return This builder, for convenience.
 * @throws IllegalStateException If {@link #buildPlayerComponents()} has already been called.
 */
public BufferSizeAdaptationBuilder setStartUpTrackSelectionParameters(float bandwidthFraction,int minBufferForQualityIncreaseMs){
  Assertions.checkState(!buildCalled);
  this.startUpBandwidthFraction=bandwidthFraction;
  this.startUpMinBufferForQualityIncreaseMs=minBufferForQualityIncreaseMs;
  return this;
}
