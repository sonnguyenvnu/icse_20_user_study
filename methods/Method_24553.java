/** 
 * <h3>Advanced</h3> This will handle both ints, bytes and chars transparently.
 * @param src data to write
 */
public void write(int src){
  try {
    port.writeInt(src);
  }
 catch (  SerialPortException e) {
    throw new RuntimeException("Error writing to serial port " + e.getPortName() + ": " + e.getExceptionType());
  }
}
