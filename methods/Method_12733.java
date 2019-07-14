/** 
 * Remove the service from service registry
 * @param component
 * @return
 */
public Service forgetService(ComponentName component){
synchronized (this.mServices) {
    Service service=this.mServices.remove(component);
    this.mServiceCounters.remove(service);
    return service;
  }
}
