@Override public <T>void removeReference(ReferenceParam<T> referenceParam){
  Reference reference=getReferenceFromReferenceParam(referenceParam);
  ComponentName referenceComponentName=ComponentNameFactory.createComponentName(ReferenceComponent.REFERENCE_COMPONENT_TYPE,reference.getInterfaceType(),reference.getUniqueId() + "#" + ReferenceRegisterHelper.generateBindingHashCode(reference));
  Collection<ComponentInfo> referenceComponents=sofaRuntimeContext.getComponentManager().getComponentInfosByType(ReferenceComponent.REFERENCE_COMPONENT_TYPE);
  for (  ComponentInfo referenceComponent : referenceComponents) {
    if (referenceComponent.getName().equals(referenceComponentName)) {
      sofaRuntimeContext.getComponentManager().unregister(referenceComponent);
    }
  }
}
