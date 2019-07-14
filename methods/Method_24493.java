/** 
 * Configures a pin to act either as input or output
 * @param pin GPIO pin
 * @param mode GPIO.INPUT, GPIO.INPUT_PULLUP, GPIO.INPUT_PULLDOWN, or GPIO.OUTPUT
 * @see digitalRead
 * @see digitalWrite
 * @see releasePin
 * @webref
 */
public static void pinMode(int pin,int mode){
  checkValidPin(pin);
  if (NativeInterface.isSimulated()) {
    return;
  }
  String fn="/sys/class/gpio/export";
  int ret=NativeInterface.writeFile(fn,Integer.toString(pin));
  if (ret < 0) {
    if (ret == -2) {
      System.err.println("Make sure your kernel is compiled with GPIO_SYSFS enabled");
    }
    if (ret == -22) {
      System.err.println("GPIO pin " + pin + " does not seem to be available on your platform");
    }
    if (ret != -16) {
      throw new RuntimeException(fn + ": " + NativeInterface.getError(ret));
    }
  }
  fn=String.format("/sys/class/gpio/gpio%d/direction",pin);
  String out;
  if (mode == INPUT) {
    out="in";
    NativeInterface.raspbianGpioMemSetPinBias(pin,mode);
  }
 else   if (mode == OUTPUT) {
    if (values.get(pin)) {
      out="high";
    }
 else {
      out="low";
    }
  }
 else   if (mode == INPUT_PULLUP || mode == INPUT_PULLDOWN) {
    out="in";
    ret=NativeInterface.raspbianGpioMemSetPinBias(pin,mode);
    if (ret == -2) {
      System.err.println("Setting pullup or pulldown resistors is currently only supported on the Raspberry Pi running Raspbian. Continuing without.");
    }
 else     if (ret < 0) {
      System.err.println("Error setting pullup or pulldown resistors: " + NativeInterface.getError(ret) + ". Continuing without.");
    }
  }
 else {
    throw new IllegalArgumentException("Unknown mode");
  }
  long start=System.currentTimeMillis();
  do {
    ret=NativeInterface.writeFile(fn,out);
    if (ret == -13) {
      Thread.yield();
    }
  }
 while (ret == -13 && System.currentTimeMillis() - start < 500);
  if (ret < 0) {
    throw new RuntimeException(fn + ": " + NativeInterface.getError(ret));
  }
}
