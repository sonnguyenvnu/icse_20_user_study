/** 
 * Adds bytes to be written to the device
 * @param out bytes to be written
 * @see beginTransmission
 * @see read
 * @see endTransmission
 * @webref
 */
public void write(byte[] out){
  if (!transmitting) {
    throw new RuntimeException("beginTransmisson has not been called");
  }
  if (this.out == null) {
    this.out=out;
  }
 else {
    byte[] tmp=new byte[this.out.length + out.length];
    System.arraycopy(this.out,0,tmp,0,this.out.length);
    System.arraycopy(out,0,tmp,this.out.length,out.length);
    this.out=tmp;
  }
}
