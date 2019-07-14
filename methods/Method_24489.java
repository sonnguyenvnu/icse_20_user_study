/** 
 * Sets an output pin to be either high or low
 * @param pin GPIO pin
 * @param value GPIO.HIGH (1) or GPIO.LOW (0)
 * @see pinMode
 * @see digitalRead
 * @webref
 */
public static void digitalWrite(int pin,int value){
  checkValidPin(pin);
  String out;
  if (value == LOW) {
    values.clear(pin);
    out="0";
  }
 else   if (value == HIGH) {
    values.set(pin);
    out="1";
  }
 else {
    System.err.println("Only GPIO.LOW and GPIO.HIGH, 0 and 1, or true and false, can be used.");
    throw new IllegalArgumentException("Illegal value");
  }
  if (NativeInterface.isSimulated()) {
    return;
  }
  String fn=String.format("/sys/class/gpio/gpio%d/value",pin);
  int ret=NativeInterface.writeFile(fn,out);
  if (ret < 0) {
    if (ret != -2) {
      throw new RuntimeException(NativeInterface.getError(ret));
    }
  }
}
