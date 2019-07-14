/** 
 * Converts a sample bit depth to a corresponding PCM encoding constant.
 * @param bitDepth The bit depth. Supported values are 8, 16, 24 and 32.
 * @return The corresponding encoding. One of {@link C#ENCODING_PCM_8BIT}, {@link C#ENCODING_PCM_16BIT},  {@link C#ENCODING_PCM_24BIT} and{@link C#ENCODING_PCM_32BIT}. If the bit depth is unsupported then {@link C#ENCODING_INVALID} is returned.
 */
@C.PcmEncoding public static int getPcmEncoding(int bitDepth){
switch (bitDepth) {
case 8:
    return C.ENCODING_PCM_8BIT;
case 16:
  return C.ENCODING_PCM_16BIT;
case 24:
return C.ENCODING_PCM_24BIT;
case 32:
return C.ENCODING_PCM_32BIT;
default :
return C.ENCODING_INVALID;
}
}
