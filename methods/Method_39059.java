/** 
 * Registers component instance and wires it with internal container. Warning: in this moment we can not guarantee that all other components are registered, replaced or configuration is update; therefore DO NOT USE injection, unless you are absolutely sure it works.
 */
public void registerComponentInstance(final String name,final Object componentInstance){
  log.debug(() -> "Madvoc WebApp component: [" + name + "] --> " + componentInstance.getClass().getName());
  madpc.removeBean(name);
  madpc.addBean(name,componentInstance);
}
