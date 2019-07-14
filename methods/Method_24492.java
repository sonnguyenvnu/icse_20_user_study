/** 
 * Enables an interrupt for an input pin
 * @param pin GPIO pin
 * @param mode what to wait for: GPIO.CHANGE, GPIO.FALLING or GPIO.RISING
 * @see waitForInterrupt
 * @see disableInterrupt
 */
protected static void enableInterrupt(int pin,int mode){
  checkValidPin(pin);
  String out;
  if (mode == NONE) {
    out="none";
  }
 else   if (mode == CHANGE) {
    out="both";
  }
 else   if (mode == FALLING) {
    out="falling";
  }
 else   if (mode == RISING) {
    out="rising";
  }
 else {
    throw new IllegalArgumentException("Unknown mode");
  }
  if (NativeInterface.isSimulated()) {
    return;
  }
  String fn=String.format("/sys/class/gpio/gpio%d/edge",pin);
  int ret=NativeInterface.writeFile(fn,out);
  if (ret < 0) {
    if (ret == -2) {
      System.err.println("Make sure your called pinMode on the input pin");
    }
    throw new RuntimeException(NativeInterface.getError(ret));
  }
}
