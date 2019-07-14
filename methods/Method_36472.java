@Override public void activate() throws ServiceRuntimeException {
  if (componentStatus != ComponentStatus.RESOLVED) {
    return;
  }
  ComponentManager componentManager=sofaRuntimeContext.getComponentManager();
  ComponentName extensionPointComponentName=extension.getTargetComponentName();
  ComponentInfo extensionPointComponentInfo=componentManager.getComponentInfo(extensionPointComponentName);
  if (extensionPointComponentInfo == null || !extensionPointComponentInfo.isActivated()) {
    return;
  }
  loadContributions(((ExtensionPointComponent)extensionPointComponentInfo).getExtensionPoint(),extension);
  Object target=extensionPointComponentInfo.getImplementation().getTarget();
  if (target instanceof Extensible) {
    try {
      ((Extensible)target).registerExtension(extension);
    }
 catch (    Exception e) {
      throw new ServiceRuntimeException(e);
    }
  }
 else {
    Method method=ReflectionUtils.findMethod(target.getClass(),"registerExtension",Extension.class);
    ReflectionUtils.invokeMethod(method,target,extension);
  }
  componentStatus=ComponentStatus.ACTIVATED;
}
