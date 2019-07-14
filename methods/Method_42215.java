/** 
 * Allow the Chrome Custom Tabs service to disconnect and GC.
 */
public void destroy(){
  context.unbindService(serviceConnection);
}
