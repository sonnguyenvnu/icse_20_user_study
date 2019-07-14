/** 
 * If Play Services is available on this device, start a service to register it with Google Cloud Messaging.
 */
public void registerDevice(){
  if (!this.playServicesCapability.isCapable()) {
    return;
  }
  DispatcherKt.dispatchJob(this.context,RegisterService.class,RegisterService.REGISTER_SERVICE);
}
