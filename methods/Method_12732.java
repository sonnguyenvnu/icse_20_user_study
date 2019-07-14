/** 
 * Put the started service into service registry, and then increase the counter associate with the service
 * @param component
 * @param service
 */
public void rememberService(ComponentName component,Service service){
synchronized (this.mServices) {
    this.mServices.put(component,service);
    this.mServiceCounters.put(service,new AtomicInteger(0));
  }
}
