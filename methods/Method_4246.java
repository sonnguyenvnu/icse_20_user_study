/** 
 * Returns the framework  {@link MediaFormat} that can be used to configure a {@link MediaCodec}for decoding the given  {@link Format} for playback.
 * @param format The format of the media.
 * @param codecMimeType The MIME type handled by the codec.
 * @param codecMaxInputSize The maximum input size supported by the codec.
 * @param codecOperatingRate The codec operating rate, or {@link #CODEC_OPERATING_RATE_UNSET} ifno codec operating rate should be set.
 * @return The framework media format.
 */
@SuppressLint("InlinedApi") protected MediaFormat getMediaFormat(Format format,String codecMimeType,int codecMaxInputSize,float codecOperatingRate){
  MediaFormat mediaFormat=new MediaFormat();
  mediaFormat.setString(MediaFormat.KEY_MIME,codecMimeType);
  mediaFormat.setInteger(MediaFormat.KEY_CHANNEL_COUNT,format.channelCount);
  mediaFormat.setInteger(MediaFormat.KEY_SAMPLE_RATE,format.sampleRate);
  MediaFormatUtil.setCsdBuffers(mediaFormat,format.initializationData);
  MediaFormatUtil.maybeSetInteger(mediaFormat,MediaFormat.KEY_MAX_INPUT_SIZE,codecMaxInputSize);
  if (Util.SDK_INT >= 23) {
    mediaFormat.setInteger(MediaFormat.KEY_PRIORITY,0);
    if (codecOperatingRate != CODEC_OPERATING_RATE_UNSET) {
      mediaFormat.setFloat(MediaFormat.KEY_OPERATING_RATE,codecOperatingRate);
    }
  }
  return mediaFormat;
}
