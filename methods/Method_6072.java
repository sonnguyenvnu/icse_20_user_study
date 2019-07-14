/** 
 * Returns the framework  {@link MediaFormat} that should be used to configure the decoder.
 * @param format The format of media.
 * @param codecMaxValues Codec max values that should be used when configuring the decoder.
 * @param codecOperatingRate The codec operating rate, or {@link #CODEC_OPERATING_RATE_UNSET} ifno codec operating rate should be set.
 * @param deviceNeedsNoPostProcessWorkaround Whether the device is known to do post processing bydefault that isn't compatible with ExoPlayer.
 * @param tunnelingAudioSessionId The audio session id to use for tunneling, or {@link C#AUDIO_SESSION_ID_UNSET} if tunneling should not be enabled.
 * @return The framework {@link MediaFormat} that should be used to configure the decoder.
 */
@SuppressLint("InlinedApi") protected MediaFormat getMediaFormat(Format format,CodecMaxValues codecMaxValues,float codecOperatingRate,boolean deviceNeedsNoPostProcessWorkaround,int tunnelingAudioSessionId){
  MediaFormat mediaFormat=new MediaFormat();
  mediaFormat.setString(MediaFormat.KEY_MIME,format.sampleMimeType);
  mediaFormat.setInteger(MediaFormat.KEY_WIDTH,format.width);
  mediaFormat.setInteger(MediaFormat.KEY_HEIGHT,format.height);
  MediaFormatUtil.setCsdBuffers(mediaFormat,format.initializationData);
  MediaFormatUtil.maybeSetFloat(mediaFormat,MediaFormat.KEY_FRAME_RATE,format.frameRate);
  MediaFormatUtil.maybeSetInteger(mediaFormat,MediaFormat.KEY_ROTATION,format.rotationDegrees);
  MediaFormatUtil.maybeSetColorInfo(mediaFormat,format.colorInfo);
  mediaFormat.setInteger(MediaFormat.KEY_MAX_WIDTH,codecMaxValues.width);
  mediaFormat.setInteger(MediaFormat.KEY_MAX_HEIGHT,codecMaxValues.height);
  MediaFormatUtil.maybeSetInteger(mediaFormat,MediaFormat.KEY_MAX_INPUT_SIZE,codecMaxValues.inputSize);
  if (Util.SDK_INT >= 23) {
    mediaFormat.setInteger(MediaFormat.KEY_PRIORITY,0);
    if (codecOperatingRate != CODEC_OPERATING_RATE_UNSET) {
      mediaFormat.setFloat(MediaFormat.KEY_OPERATING_RATE,codecOperatingRate);
    }
  }
  if (deviceNeedsNoPostProcessWorkaround) {
    mediaFormat.setInteger("no-post-process",1);
    mediaFormat.setInteger("auto-frc",0);
  }
  if (tunnelingAudioSessionId != C.AUDIO_SESSION_ID_UNSET) {
    configureTunnelingV21(mediaFormat,tunnelingAudioSessionId);
  }
  return mediaFormat;
}
