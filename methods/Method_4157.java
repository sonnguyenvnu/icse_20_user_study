/** 
 * Reads the number of audio samples represented by the given E-AC-3 syncframe. The buffer's position is not modified.
 * @param buffer The {@link ByteBuffer} from which to read the syncframe.
 * @return The number of audio samples represented by the syncframe.
 */
public static int parseEAc3SyncframeAudioSampleCount(ByteBuffer buffer){
  int fscod=(buffer.get(buffer.position() + 4) & 0xC0) >> 6;
  return AUDIO_SAMPLES_PER_AUDIO_BLOCK * (fscod == 0x03 ? 6 : BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[(buffer.get(buffer.position() + 4) & 0x30) >> 4]);
}
