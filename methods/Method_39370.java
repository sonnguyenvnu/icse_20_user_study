/** 
 * Resolves wiring mode by checking if default and <code>null</code> values.
 */
protected WiringMode resolveWiringMode(WiringMode wiringMode){
  if ((wiringMode == null) || (wiringMode == WiringMode.DEFAULT)) {
    wiringMode=defaultWiringMode;
  }
  return wiringMode;
}
