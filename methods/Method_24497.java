/** 
 * Begins a transmission to an attached device
 * @see write
 * @see read
 * @see endTransmission
 * @webref
 */
public void beginTransmission(int slave){
  if (0x78 <= slave) {
    System.err.println("beginTransmission expects a 7 bit address, try shifting one bit to the right");
    throw new IllegalArgumentException("Illegal address");
  }
  this.slave=slave;
  transmitting=true;
  out=null;
}
