@Override public void removeService(Class<?> interfaceClass,String uniqueId,int millisecondsToDelay){
  if (millisecondsToDelay < 0) {
    throw new IllegalArgumentException("Argument delay must be a positive integer or zero.");
  }
  Collection<ComponentInfo> serviceComponents=sofaRuntimeContext.getComponentManager().getComponentInfosByType(ServiceComponent.SERVICE_COMPONENT_TYPE);
  for (  ComponentInfo componentInfo : serviceComponents) {
    if (!(componentInfo instanceof ServiceComponent)) {
      continue;
    }
    ServiceComponent serviceComponent=(ServiceComponent)componentInfo;
    if (serviceComponent.getService().getInterfaceType() == interfaceClass && serviceComponent.getService().getUniqueId().equals(uniqueId)) {
      Map<String,Property> properties=serviceComponent.getProperties();
      Property property=new Property();
      property.setValue(millisecondsToDelay);
      properties.put(ServiceComponent.UNREGISTER_DELAY_MILLISECONDS,property);
      sofaRuntimeContext.getComponentManager().unregister(serviceComponent);
    }
  }
}
