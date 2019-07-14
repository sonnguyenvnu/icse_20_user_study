/** 
 * Updates the codec operating rate.
 * @throws ExoPlaybackException If an error occurs releasing or initializing a codec.
 */
private void updateCodecOperatingRate() throws ExoPlaybackException {
  if (Util.SDK_INT < 23) {
    return;
  }
  float newCodecOperatingRate=getCodecOperatingRateV23(rendererOperatingRate,codecFormat,getStreamFormats());
  if (codecOperatingRate == newCodecOperatingRate) {
  }
 else   if (newCodecOperatingRate == CODEC_OPERATING_RATE_UNSET) {
    drainAndReinitializeCodec();
  }
 else   if (codecOperatingRate != CODEC_OPERATING_RATE_UNSET || newCodecOperatingRate > assumedMinimumCodecOperatingRate) {
    Bundle codecParameters=new Bundle();
    codecParameters.putFloat(MediaFormat.KEY_OPERATING_RATE,newCodecOperatingRate);
    codec.setParameters(codecParameters);
    codecOperatingRate=newCodecOperatingRate;
  }
}
