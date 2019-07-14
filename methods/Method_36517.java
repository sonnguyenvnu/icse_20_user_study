public static Object registerReference(Reference reference,BindingAdapterFactory bindingAdapterFactory,SofaRuntimeContext sofaRuntimeContext){
  Binding binding=(Binding)reference.getBindings().toArray()[0];
  if (!binding.getBindingType().equals(JvmBinding.JVM_BINDING_TYPE) && !SofaRuntimeProperties.isDisableJvmFirst(sofaRuntimeContext) && reference.isJvmFirst()) {
    reference.addBinding(new JvmBinding());
  }
  ComponentManager componentManager=sofaRuntimeContext.getComponentManager();
  ReferenceComponent referenceComponent=new ReferenceComponent(reference,new DefaultImplementation(),bindingAdapterFactory,sofaRuntimeContext);
  if (componentManager.isRegistered(referenceComponent.getName())) {
    return componentManager.getComponentInfo(referenceComponent.getName()).getImplementation().getTarget();
  }
  ComponentInfo componentInfo=componentManager.registerAndGet(referenceComponent);
  return componentInfo.getImplementation().getTarget();
}
