/** 
 * Whether the decoder supports audio with a given channel count. <p> Must not be called if the device SDK version is less than 21.
 * @param channelCount The channel count.
 * @return Whether the decoder supports audio with the given channel count.
 */
@TargetApi(21) public boolean isAudioChannelCountSupportedV21(int channelCount){
  if (capabilities == null) {
    logNoSupport("channelCount.caps");
    return false;
  }
  AudioCapabilities audioCapabilities=capabilities.getAudioCapabilities();
  if (audioCapabilities == null) {
    logNoSupport("channelCount.aCaps");
    return false;
  }
  int maxInputChannelCount=adjustMaxInputChannelCount(name,mimeType,audioCapabilities.getMaxInputChannelCount());
  if (maxInputChannelCount < channelCount) {
    logNoSupport("channelCount.support, " + channelCount);
    return false;
  }
  return true;
}
