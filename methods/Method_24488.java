/** 
 * Returns the value of an input pin
 * @param pin GPIO pin
 * @return GPIO.HIGH (1) or GPIO.LOW (0)
 * @see pinMode
 * @see digitalWrite
 * @webref
 */
public static int digitalRead(int pin){
  checkValidPin(pin);
  if (NativeInterface.isSimulated()) {
    return LOW;
  }
  String fn=String.format("/sys/class/gpio/gpio%d/value",pin);
  byte in[]=new byte[2];
  int ret=NativeInterface.readFile(fn,in);
  if (ret < 0) {
    throw new RuntimeException(NativeInterface.getError(ret));
  }
 else   if (1 <= ret && in[0] == '0') {
    return LOW;
  }
 else   if (1 <= ret && in[0] == '1') {
    return HIGH;
  }
 else {
    System.err.print("Read " + ret + " bytes");
    if (0 < ret) {
      System.err.format(", first byte is 0x%02x" + in[0]);
    }
    System.err.println();
    throw new RuntimeException("Unexpected value");
  }
}
