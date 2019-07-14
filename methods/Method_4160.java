/** 
 * Reads the number of audio samples represented by a TrueHD syncframe. The buffer's position is not modified.
 * @param buffer The {@link ByteBuffer} from which to read the syncframe.
 * @param offset The offset of the start of the syncframe relative to the buffer's position.
 * @return The number of audio samples represented by the syncframe.
 */
public static int parseTrueHdSyncframeAudioSampleCount(ByteBuffer buffer,int offset){
  boolean isMlp=(buffer.get(buffer.position() + offset + 7) & 0xFF) == 0xBB;
  return 40 << ((buffer.get(buffer.position() + offset + (isMlp ? 9 : 8)) >> 4) & 0x07);
}
