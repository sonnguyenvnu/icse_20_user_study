/** 
 * Sets the elapsed time for this stopwatch to zero, and places it in a stopped state.
 * @return this {@code Stopwatch} instance
 */
public Stopwatch reset(){
  elapsedNanos=0;
  isRunning=false;
  return this;
}
