/** 
 * Returns the AAC audio object type as specified in 14496-3 (2005) Table 1.14.
 * @param bitArray The bit array containing the audio specific configuration.
 * @return The audio object type.
 */
private static int getAacAudioObjectType(ParsableBitArray bitArray){
  int audioObjectType=bitArray.readBits(5);
  if (audioObjectType == AUDIO_OBJECT_TYPE_ESCAPE) {
    audioObjectType=32 + bitArray.readBits(6);
  }
  return audioObjectType;
}
