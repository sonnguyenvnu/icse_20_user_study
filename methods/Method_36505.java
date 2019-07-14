@Override public void removeReference(Class<?> interfaceClass,String uniqueId){
  Collection<ComponentInfo> referenceComponents=sofaRuntimeContext.getComponentManager().getComponentInfosByType(ReferenceComponent.REFERENCE_COMPONENT_TYPE);
  for (  ComponentInfo componentInfo : referenceComponents) {
    if (!(componentInfo instanceof ReferenceComponent)) {
      continue;
    }
    ReferenceComponent referenceComponent=(ReferenceComponent)componentInfo;
    if (referenceComponent.getReference().getInterfaceType() == interfaceClass && referenceComponent.getReference().getUniqueId().equals(uniqueId)) {
      sofaRuntimeContext.getComponentManager().unregister(referenceComponent);
    }
  }
}
