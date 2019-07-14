@Override public boolean supportsOutput(int channelCount,@C.Encoding int encoding){
  if (Util.isEncodingLinearPcm(encoding)) {
    return encoding != C.ENCODING_PCM_FLOAT || Util.SDK_INT >= 21;
  }
 else {
    return audioCapabilities != null && audioCapabilities.supportsEncoding(encoding) && (channelCount == Format.NO_VALUE || channelCount <= audioCapabilities.getMaxChannelCount());
  }
}
