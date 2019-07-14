/** 
 * Returns whether the decoder may support decoding the given  {@code format}.
 * @param format The input media format.
 * @return Whether the decoder may support decoding the given {@code format}.
 * @throws MediaCodecUtil.DecoderQueryException Thrown if an error occurs while querying decoders.
 */
public boolean isFormatSupported(Format format) throws MediaCodecUtil.DecoderQueryException {
  if (!isCodecSupported(format.codecs)) {
    return false;
  }
  if (isVideo) {
    if (format.width <= 0 || format.height <= 0) {
      return true;
    }
    if (Util.SDK_INT >= 21) {
      return isVideoSizeAndRateSupportedV21(format.width,format.height,format.frameRate);
    }
 else {
      boolean isFormatSupported=format.width * format.height <= MediaCodecUtil.maxH264DecodableFrameSize();
      if (!isFormatSupported) {
        logNoSupport("legacyFrameSize, " + format.width + "x" + format.height);
      }
      return isFormatSupported;
    }
  }
 else {
    return Util.SDK_INT < 21 || ((format.sampleRate == Format.NO_VALUE || isAudioSampleRateSupportedV21(format.sampleRate)) && (format.channelCount == Format.NO_VALUE || isAudioChannelCountSupportedV21(format.channelCount)));
  }
}
