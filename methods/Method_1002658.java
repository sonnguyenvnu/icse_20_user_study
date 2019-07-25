/** 
 * Update healthy servers and start to schedule healthcheck. A subclass being initialized with this constructor must call  {@link #init()} before start being used.
 */
protected void init(){
  checkAndUpdateHealthyServers().join();
  scheduleCheckAndUpdateHealthyServers();
}
