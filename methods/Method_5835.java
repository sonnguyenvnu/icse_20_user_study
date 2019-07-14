/** 
 * Parses an AudioSpecificConfig, as defined in ISO 14496-3 1.6.2.1
 * @param bitArray A {@link ParsableBitArray} containing the AudioSpecificConfig to parse. Theposition is advanced to the end of the AudioSpecificConfig.
 * @param forceReadToEnd Whether the entire AudioSpecificConfig should be read. Required forknowing the length of the configuration payload.
 * @return A pair consisting of the sample rate in Hz and the channel count.
 * @throws ParserException If the AudioSpecificConfig cannot be parsed as it's not supported.
 */
public static Pair<Integer,Integer> parseAacAudioSpecificConfig(ParsableBitArray bitArray,boolean forceReadToEnd) throws ParserException {
  int audioObjectType=getAacAudioObjectType(bitArray);
  int sampleRate=getAacSamplingFrequency(bitArray);
  int channelConfiguration=bitArray.readBits(4);
  if (audioObjectType == AUDIO_OBJECT_TYPE_SBR || audioObjectType == AUDIO_OBJECT_TYPE_PS) {
    sampleRate=getAacSamplingFrequency(bitArray);
    audioObjectType=getAacAudioObjectType(bitArray);
    if (audioObjectType == AUDIO_OBJECT_TYPE_ER_BSAC) {
      channelConfiguration=bitArray.readBits(4);
    }
  }
  if (forceReadToEnd) {
switch (audioObjectType) {
case 1:
case 2:
case 3:
case 4:
case 6:
case 7:
case 17:
case 19:
case 20:
case 21:
case 22:
case 23:
      parseGaSpecificConfig(bitArray,audioObjectType,channelConfiguration);
    break;
default :
  throw new ParserException("Unsupported audio object type: " + audioObjectType);
}
switch (audioObjectType) {
case 17:
case 19:
case 20:
case 21:
case 22:
case 23:
int epConfig=bitArray.readBits(2);
if (epConfig == 2 || epConfig == 3) {
throw new ParserException("Unsupported epConfig: " + epConfig);
}
break;
}
}
int channelCount=AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[channelConfiguration];
Assertions.checkArgument(channelCount != AUDIO_SPECIFIC_CONFIG_CHANNEL_CONFIGURATION_INVALID);
return Pair.create(sampleRate,channelCount);
}
