/** 
 * Converts the provided 32-bit integer to a 32-bit float value and writes it to  {@code buffer}.
 * @param pcm32BitInt The 32-bit integer value to convert to 32-bit float in [-1.0, 1.0].
 * @param buffer The output buffer.
 */
private static void writePcm32BitFloat(int pcm32BitInt,ByteBuffer buffer){
  float pcm32BitFloat=(float)(PCM_32_BIT_INT_TO_PCM_32_BIT_FLOAT_FACTOR * pcm32BitInt);
  int floatBits=Float.floatToIntBits(pcm32BitFloat);
  if (floatBits == FLOAT_NAN_AS_INT) {
    floatBits=Float.floatToIntBits((float)0.0);
  }
  buffer.putInt(floatBits);
}
