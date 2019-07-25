/** 
 * Like tick(), but doesn't reset the counter. 
 */
public long report(String msg){
  long currentTime=System.currentTimeMillis();
  long elapsed=currentTime - startTime;
  System.out.println(msg + " time (ms) =  " + (elapsed));
  return elapsed;
}
