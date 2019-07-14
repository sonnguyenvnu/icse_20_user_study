/** 
 * Returns whether  {@code encoding} is one of the linear PCM encodings.
 * @param encoding The encoding of the audio data.
 * @return Whether the encoding is one of the PCM encodings.
 */
public static boolean isEncodingLinearPcm(@C.Encoding int encoding){
  return encoding == C.ENCODING_PCM_8BIT || encoding == C.ENCODING_PCM_16BIT || encoding == C.ENCODING_PCM_24BIT || encoding == C.ENCODING_PCM_32BIT || encoding == C.ENCODING_PCM_FLOAT;
}
