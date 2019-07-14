/** 
 * Registers Madvoc component with given name.
 */
public void registerComponent(final String name,final Class component){
  log.debug(() -> "Madvoc WebApp component: [" + name + "] --> " + component.getName());
  madpc.removeBean(name);
  madpc.registerPetiteBean(component,name,null,null,false,null);
}
