/** 
 * Attaches a servo motor to a GPIO pin using custom pulse widths
 * @param minPulse minimum pulse width in microseconds (default: 544, same as on Arduino)
 * @param maxPulse maximum pulse width in microseconds (default: 2400, same as on Arduino)
 * @webref
 */
public void attach(int pin,int minPulse,int maxPulse){
  detach();
  this.pin=pin;
  this.minPulse=minPulse;
  this.maxPulse=maxPulse;
}
