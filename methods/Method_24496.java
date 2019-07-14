/** 
 * Waits for the value of an input pin to change Make sure to setup the interrupt with enableInterrupt() before calling this function. A timeout value of -1 waits indefinitely.
 * @param pin GPIO pin
 * @param timeout don't wait more than timeout milliseconds
 * @return true if the interrupt occured, false if the timeout occured
 * @see enableInterrupt
 * @see disableInterrupt
 */
protected static boolean waitForInterrupt(int pin,int timeout){
  checkValidPin(pin);
  if (NativeInterface.isSimulated()) {
    try {
      Thread.sleep(200);
    }
 catch (    InterruptedException e) {
    }
    return true;
  }
  String fn=String.format("/sys/class/gpio/gpio%d/value",pin);
  int ret=NativeInterface.pollDevice(fn,timeout);
  if (ret < 0) {
    if (ret == -2) {
      System.err.println("Make sure your called pinMode on the input pin");
    }
    throw new RuntimeException(NativeInterface.getError(ret));
  }
 else   if (ret == 0) {
    return false;
  }
 else {
    return true;
  }
}
