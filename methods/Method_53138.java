/** 
 * @param method   the method invoked
 * @param time     the method execution time
 * @param success success
 */
public synchronized void methodCalled(String method,long time,boolean success){
  getMethodStatistics(method).increment(time,success);
  API_STATS_CALCULATOR.increment(time,success);
}
