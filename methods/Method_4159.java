/** 
 * Returns the number of audio samples represented by the given TrueHD syncframe, or 0 if the buffer is not the start of a syncframe.
 * @param syncframe The bytes from which to read the syncframe. Must be at least {@link #TRUEHD_SYNCFRAME_PREFIX_LENGTH} bytes long.
 * @return The number of audio samples represented by the syncframe, or 0 if the buffer doesn'tcontain the start of a syncframe.
 */
public static int parseTrueHdSyncframeAudioSampleCount(byte[] syncframe){
  if (syncframe[4] != (byte)0xF8 || syncframe[5] != (byte)0x72 || syncframe[6] != (byte)0x6F || (syncframe[7] & 0xFE) != 0xBA) {
    return 0;
  }
  boolean isMlp=(syncframe[7] & 0xFF) == 0xBB;
  return 40 << ((syncframe[isMlp ? 9 : 8] >> 4) & 0x07);
}
