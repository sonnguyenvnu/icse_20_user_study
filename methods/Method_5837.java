/** 
 * Builds a simple AudioSpecificConfig, as defined in ISO 14496-3 1.6.2.1
 * @param audioObjectType The audio object type.
 * @param sampleRateIndex The sample rate index.
 * @param channelConfig The channel configuration.
 * @return The AudioSpecificConfig.
 */
public static byte[] buildAacAudioSpecificConfig(int audioObjectType,int sampleRateIndex,int channelConfig){
  byte[] specificConfig=new byte[2];
  specificConfig[0]=(byte)(((audioObjectType << 3) & 0xF8) | ((sampleRateIndex >> 1) & 0x07));
  specificConfig[1]=(byte)(((sampleRateIndex << 7) & 0x80) | ((channelConfig << 3) & 0x78));
  return specificConfig;
}
