/** 
 * Registers Madvoc component with given name.
 */
public <T>void registerComponent(final String name,final Class<T> component,final Consumer<T> consumer){
  log.debug(() -> "Madvoc WebApp component: [" + name + "] --> " + component.getName());
  madpc.removeBean(name);
  madpc.registerPetiteBean(component,name,null,null,false,consumer);
}
