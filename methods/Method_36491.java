/** 
 * Find corresponding  {@link ServiceComponent} in specified {@link ComponentManager}
 * @param uniqueId
 * @param interfaceType
 * @param componentManager
 * @return
 */
private ServiceComponent findServiceComponent(String uniqueId,String interfaceType,ComponentManager componentManager){
  Collection<ComponentInfo> components=componentManager.getComponentInfosByType(ServiceComponent.SERVICE_COMPONENT_TYPE);
  for (  ComponentInfo c : components) {
    ServiceComponent component=(ServiceComponent)c;
    Contract serviceContract=component.getService();
    if (serviceContract.getInterfaceType().getCanonicalName().equals(interfaceType) && uniqueId.equals(serviceContract.getUniqueId())) {
      return component;
    }
  }
  return null;
}
