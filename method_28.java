/** 
 * Set the number of threads that should be used for high priority requests (i.e. recovery reads and adds, and fencing)
 * @param numThreads number of threads to handle high priority requests.
 * @return server configuration
 */
public ServerConfiguration _XXXXX_(int numThreads){
  setProperty(NUM_HIGH_PRIORITY_WORKER_THREADS,numThreads);
  return this;
}