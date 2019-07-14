/** 
 * If Play Services is available on this device, start a service to unregister it with Google Cloud Messaging.
 */
public void unregisterDevice(){
  if (!this.playServicesCapability.isCapable()) {
    return;
  }
  DispatcherKt.dispatchJob(this.context,UnregisterService.class,UnregisterService.UNREGISTER_SERVICE);
}
