/** 
 * Returns the number of audio samples represented by the given DTS frame.
 * @param data The frame to parse.
 * @return The number of audio samples represented by the frame.
 */
public static int parseDtsAudioSampleCount(byte[] data){
  int nblks;
switch (data[0]) {
case FIRST_BYTE_LE:
    nblks=((data[5] & 0x01) << 6) | ((data[4] & 0xFC) >> 2);
  break;
case FIRST_BYTE_14B_LE:
nblks=((data[4] & 0x07) << 4) | ((data[7] & 0x3C) >> 2);
break;
case FIRST_BYTE_14B_BE:
nblks=((data[5] & 0x07) << 4) | ((data[6] & 0x3C) >> 2);
break;
default :
nblks=((data[4] & 0x01) << 6) | ((data[5] & 0xFC) >> 2);
}
return (nblks + 1) * 32;
}
