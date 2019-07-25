/** 
 * Restarts timer execution and modifies timer delays.
 * @param initialDelay delay before the first timer cycle run
 * @param delay        delay between timer cycles
 * @return this timer
 */
public WebTimer restart(final String initialDelay,final String delay){
  stopExec();
  setInitialDelay(initialDelay);
  setDelay(delay);
  startExec();
  return this;
}
