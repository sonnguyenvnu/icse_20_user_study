/** 
 * Builds a simple HE-AAC LC AudioSpecificConfig, as defined in ISO 14496-3 1.6.2.1
 * @param sampleRate The sample rate in Hz.
 * @param numChannels The number of channels.
 * @return The AudioSpecificConfig.
 */
public static byte[] buildAacLcAudioSpecificConfig(int sampleRate,int numChannels){
  int sampleRateIndex=C.INDEX_UNSET;
  for (int i=0; i < AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE.length; ++i) {
    if (sampleRate == AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[i]) {
      sampleRateIndex=i;
    }
  }
  int channelConfig=C.INDEX_UNSET;
  for (int i=0; i < AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE.length; ++i) {
    if (numChannels == AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[i]) {
      channelConfig=i;
    }
  }
  if (sampleRate == C.INDEX_UNSET || channelConfig == C.INDEX_UNSET) {
    throw new IllegalArgumentException("Invalid sample rate or number of channels: " + sampleRate + ", " + numChannels);
  }
  return buildAacAudioSpecificConfig(AUDIO_OBJECT_TYPE_AAC_LC,sampleRateIndex,channelConfig);
}
