/** 
 * Scans the classpath, blocking until the scan is complete. You should assign the returned  {@link ScanResult}in a try-with-resources statement, or manually close it when you are finished with it.
 * @return a {@link ScanResult} object representing the result of the scan.
 * @throws ClassGraphException if any of the worker threads throws an uncaught exception, or the scan was interrupted.
 */
public ScanResult scan(){
  return scan(DEFAULT_NUM_WORKER_THREADS);
}
