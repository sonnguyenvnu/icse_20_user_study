/** 
 * Returns whether two  {@link Format}s will cause the same codec to be configured in an identical way, excluding  {@link MediaFormat#KEY_MAX_INPUT_SIZE} and configuration that does not come fromthe  {@link Format}.
 * @param oldFormat The first format.
 * @param newFormat The second format.
 * @return Whether the two formats will cause a codec to be configured in an identical way,excluding  {@link MediaFormat#KEY_MAX_INPUT_SIZE} and configuration that does not come fromthe  {@link Format}.
 */
protected boolean areCodecConfigurationCompatible(Format oldFormat,Format newFormat){
  return Util.areEqual(oldFormat.sampleMimeType,newFormat.sampleMimeType) && oldFormat.channelCount == newFormat.channelCount && oldFormat.sampleRate == newFormat.sampleRate && oldFormat.initializationDataEquals(newFormat);
}
