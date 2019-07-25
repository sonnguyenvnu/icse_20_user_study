/** 
 * Shuts down and restarts the application handler in the current container. The <code>ApplicationHandler</code> object is re-initialized with the <code>Application</code> object initially set in the <code>LambdaContainer.getInstance()</code> call.
 */
@Override public void reload(){
  Timer.start("JERSEY_RELOAD_DEFAULT");
  jersey.onShutdown(this);
  jersey=new ApplicationHandler(app);
  jersey.onReload(this);
  jersey.onStartup(this);
  Timer.stop("JERSEY_RELOAD_DEFAULT");
}
