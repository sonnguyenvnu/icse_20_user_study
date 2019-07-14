/** 
 * Reads bytes from the attached device
 * @param len number of bytes to read
 * @return bytes read from device
 * @see beginTransmission
 * @see write
 * @see endTransmission
 * @webref
 */
public byte[] read(int len){
  if (!transmitting) {
    throw new RuntimeException("beginTransmisson has not been called");
  }
  byte[] in=new byte[len];
  if (NativeInterface.isSimulated()) {
    return in;
  }
  int ret=NativeInterface.transferI2c(handle,slave,out,in);
  transmitting=false;
  out=null;
  if (ret < 0) {
    if (ret == -5 | ret == -121) {
      System.err.println("The device did not respond. Check the cabling and whether you are using the correct address.");
    }
    throw new RuntimeException(NativeInterface.getError(ret));
  }
  return in;
}
