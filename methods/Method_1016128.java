/** 
 * Restarts timer execution and modifies timer delay.
 * @param delay delay between timer cycles
 * @return this timer
 */
public WebTimer restart(final long delay){
  stopExec();
  setInitialDelay(delay);
  setDelay(delay);
  startExec();
  return this;
}
