/** 
 * Disables an interrupt for an input pin
 * @param pin GPIO pin
 * @see enableInterrupt
 * @see waitForInterrupt
 */
protected static void disableInterrupt(int pin){
  enableInterrupt(pin,NONE);
}
