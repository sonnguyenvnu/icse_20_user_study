/** 
 * get service target
 * @return service target
 */
private Object getServiceTarget(){
  Object serviceTarget=null;
  ComponentName componentName=ComponentNameFactory.createComponentName(ServiceComponent.SERVICE_COMPONENT_TYPE,reference.getInterfaceType(),reference.getUniqueId());
  ComponentInfo componentInfo=sofaRuntimeContext.getComponentManager().getComponentInfo(componentName);
  if (componentInfo != null) {
    serviceTarget=componentInfo.getImplementation().getTarget();
  }
  return serviceTarget;
}
