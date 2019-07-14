/** 
 * Checks if the GPIO pin number can be valid Board-specific classes, such as RPI, assign -1 to pins that carry power, ground and the like.
 * @param pin GPIO pin
 */
protected static void checkValidPin(int pin){
  if (pin < 0) {
    throw new RuntimeException("Operation not supported on this pin");
  }
}
