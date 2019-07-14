/** 
 * Builds an RFC 6381 AVC codec string using the provided parameters.
 * @param profileIdc The encoding profile.
 * @param constraintsFlagsAndReservedZero2Bits The constraint flags followed by the reserved zero2 bits, all contained in the least significant byte of the integer.
 * @param levelIdc The encoding level.
 * @return An RFC 6381 AVC codec string built using the provided parameters.
 */
public static String buildAvcCodecString(int profileIdc,int constraintsFlagsAndReservedZero2Bits,int levelIdc){
  return String.format("avc1.%02X%02X%02X",profileIdc,constraintsFlagsAndReservedZero2Bits,levelIdc);
}
