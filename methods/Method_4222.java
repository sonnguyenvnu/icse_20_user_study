/** 
 * Like  {@link #parseDtsAudioSampleCount(byte[])} but reads from a {@link ByteBuffer}. The buffer's position is not modified.
 * @param buffer The {@link ByteBuffer} from which to read.
 * @return The number of audio samples represented by the syncframe.
 */
public static int parseDtsAudioSampleCount(ByteBuffer buffer){
  int position=buffer.position();
  int nblks;
switch (buffer.get(position)) {
case FIRST_BYTE_LE:
    nblks=((buffer.get(position + 5) & 0x01) << 6) | ((buffer.get(position + 4) & 0xFC) >> 2);
  break;
case FIRST_BYTE_14B_LE:
nblks=((buffer.get(position + 4) & 0x07) << 4) | ((buffer.get(position + 7) & 0x3C) >> 2);
break;
case FIRST_BYTE_14B_BE:
nblks=((buffer.get(position + 5) & 0x07) << 4) | ((buffer.get(position + 6) & 0x3C) >> 2);
break;
default :
nblks=((buffer.get(position + 4) & 0x01) << 6) | ((buffer.get(position + 5) & 0xFC) >> 2);
}
return (nblks + 1) * 32;
}
