/** 
 * Attaches a servo motor to a GPIO pin
 * @param pin GPIO pin
 * @webref
 */
public void attach(int pin){
  detach();
  this.pin=pin;
  this.minPulse=DEFAULT_MIN_PULSE;
  this.maxPulse=DEFAULT_MAX_PULSE;
}
