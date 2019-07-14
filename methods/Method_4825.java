/** 
 * Returns the value of PCR base - first 33 bits in big endian order from the PCR bytes. <p>We ignore PCR Ext, because it's too small to have any significance.
 */
private static long readPcrValueFromPcrBytes(byte[] pcrBytes){
  return (pcrBytes[0] & 0xFFL) << 25 | (pcrBytes[1] & 0xFFL) << 17 | (pcrBytes[2] & 0xFFL) << 9 | (pcrBytes[3] & 0xFFL) << 1 | (pcrBytes[4] & 0xFFL) >> 7;
}
