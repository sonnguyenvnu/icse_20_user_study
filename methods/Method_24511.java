/** 
 * Transfers data over the SPI bus
 * @param out string to send
 * @return bytes read in (array is the same length as out)
 */
public byte[] transfer(String out){
  return transfer(out.getBytes());
}
