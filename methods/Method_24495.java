/** 
 * Waits for the value of an input pin to change This function will throw a RuntimeException in case of a timeout.
 * @param timeout don't wait more than timeout milliseconds
 * @webref
 */
public static void waitFor(int pin,int mode,int timeout){
  enableInterrupt(pin,mode);
  if (waitForInterrupt(pin,timeout) == false) {
    throw new RuntimeException("Timeout occurred");
  }
}
