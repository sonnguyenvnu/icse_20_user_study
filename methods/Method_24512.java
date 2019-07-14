/** 
 * Transfers data over the SPI bus
 * @param out single byte to send, e.g. numeric literal (0 to 255, or -128 to 127)
 * @return bytes read in (array is the same length as out)
 */
public byte[] transfer(int out){
  if (out < -128 || 255 < out) {
    System.err.println("The transfer function can only operate on a single byte at a time. Call it with a value from 0 to 255, or -128 to 127.");
    throw new RuntimeException("Argument does not fit into a single byte");
  }
  byte[] tmp=new byte[1];
  tmp[0]=(byte)out;
  return transfer(tmp);
}
