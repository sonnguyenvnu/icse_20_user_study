/** 
 * Returns the value of SCR base - 33 bits in big endian order from the PS pack header, ignoring the marker bits. Note: See ISO/IEC 13818-1, Table 2-33 for details of the SCR field in pack_header. <p>We ignore SCR Ext, because it's too small to have any significance.
 */
private static long readScrValueFromPackHeader(byte[] scrBytes){
  return ((scrBytes[0] & 0b00111000L) >> 3) << 30 | (scrBytes[0] & 0b00000011L) << 28 | (scrBytes[1] & 0xFFL) << 20 | ((scrBytes[2] & 0b11111000L) >> 3) << 15 | (scrBytes[2] & 0b00000011L) << 13 | (scrBytes[3] & 0xFFL) << 5 | (scrBytes[4] & 0b11111000L) >> 3;
}
