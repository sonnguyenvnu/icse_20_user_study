@Override public void activate() throws ServiceRuntimeException {
  super.activate();
  ComponentManager componentManager=sofaRuntimeContext.getComponentManager();
  for (  ComponentInfo componentInfo : componentManager.getComponents()) {
    if (componentInfo.getType().equals(ExtensionComponent.EXTENSION_COMPONENT_TYPE) && !componentInfo.isResolved()) {
      ExtensionComponent extensionComponent=(ExtensionComponent)componentInfo;
      if (extensionComponent.getExtension().getTargetComponentName().equals(componentName)) {
        componentManager.resolvePendingResolveComponent(componentInfo.getName());
      }
    }
  }
}
