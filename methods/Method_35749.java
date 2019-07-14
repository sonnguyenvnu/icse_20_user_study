/** 
 * The total request time including added delay
 */
public int getTotalTime(){
  return getServeTime() + addedDelay;
}
