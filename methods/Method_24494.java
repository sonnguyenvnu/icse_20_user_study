/** 
 * Gives ownership of a pin back to the operating system
 * @param pin GPIO pin
 * @see pinMode
 * @webref
 */
public static void releasePin(int pin){
  checkValidPin(pin);
  if (NativeInterface.isSimulated()) {
    return;
  }
  String fn="/sys/class/gpio/unexport";
  int ret=NativeInterface.writeFile(fn,Integer.toString(pin));
  if (ret < 0) {
    if (ret == -2) {
      System.err.println("Make sure your kernel is compiled with GPIO_SYSFS enabled");
    }
    if (ret != -22) {
      throw new RuntimeException(NativeInterface.getError(ret));
    }
  }
}
