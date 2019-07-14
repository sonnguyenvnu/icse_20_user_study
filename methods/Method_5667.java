/** 
 * Called when updating the selected track to determine whether a candidate track can be selected.
 * @param format The {@link Format} of the candidate track.
 * @param trackBitrate The estimated bitrate of the track. May differ from {@link Format#bitrate}if a more accurate estimate of the current track bitrate is available.
 * @param playbackSpeed The current playback speed.
 * @param effectiveBitrate The bitrate available to this selection.
 * @return Whether this {@link Format} can be selected.
 */
@SuppressWarnings("unused") protected boolean canSelectFormat(Format format,int trackBitrate,float playbackSpeed,long effectiveBitrate){
  return Math.round(trackBitrate * playbackSpeed) <= effectiveBitrate;
}
