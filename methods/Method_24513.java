/** 
 * Transfers data over the SPI bus
 * @param out single byte to send
 * @return bytes read in (array is the same length as out)
 */
public byte[] transfer(byte out){
  return transfer(out & 0xff);
}
