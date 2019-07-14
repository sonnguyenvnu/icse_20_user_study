/** 
 * Returns the frame size for audio with  {@code channelCount} channels in the specified encoding.
 * @param pcmEncoding The encoding of the audio data.
 * @param channelCount The channel count.
 * @return The size of one audio frame in bytes.
 */
public static int getPcmFrameSize(@C.PcmEncoding int pcmEncoding,int channelCount){
switch (pcmEncoding) {
case C.ENCODING_PCM_8BIT:
    return channelCount;
case C.ENCODING_PCM_16BIT:
  return channelCount * 2;
case C.ENCODING_PCM_24BIT:
return channelCount * 3;
case C.ENCODING_PCM_32BIT:
case C.ENCODING_PCM_FLOAT:
return channelCount * 4;
case C.ENCODING_PCM_A_LAW:
case C.ENCODING_PCM_MU_LAW:
case C.ENCODING_INVALID:
case Format.NO_VALUE:
default :
throw new IllegalArgumentException();
}
}
