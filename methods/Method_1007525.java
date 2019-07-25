/** 
 * return a new default Scheduler with default queue length
 * @param numThreads the number of threads to use, or use the default if less than one
 * @return the new Scheduler
 */
public static Scheduler make(int numThreads){
  return new AffineScheduler(numThreads,0);
}
