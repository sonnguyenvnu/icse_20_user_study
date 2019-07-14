/** 
 * Returns the AAC sampling frequency (or extension sampling frequency) as specified in 14496-3 (2005) Table 1.13.
 * @param bitArray The bit array containing the audio specific configuration.
 * @return The sampling frequency.
 */
private static int getAacSamplingFrequency(ParsableBitArray bitArray){
  int samplingFrequency;
  int frequencyIndex=bitArray.readBits(4);
  if (frequencyIndex == AUDIO_SPECIFIC_CONFIG_FREQUENCY_INDEX_ARBITRARY) {
    samplingFrequency=bitArray.readBits(24);
  }
 else {
    Assertions.checkArgument(frequencyIndex < 13);
    samplingFrequency=AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[frequencyIndex];
  }
  return samplingFrequency;
}
