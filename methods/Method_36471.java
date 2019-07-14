@Override public boolean resolve(){
  if (componentStatus != ComponentStatus.REGISTERED) {
    return false;
  }
  ComponentManager componentManager=sofaRuntimeContext.getComponentManager();
  ComponentName extensionPointComponentName=extension.getTargetComponentName();
  ComponentInfo extensionPointComponentInfo=componentManager.getComponentInfo(extensionPointComponentName);
  if (extensionPointComponentInfo != null && extensionPointComponentInfo.isActivated()) {
    componentStatus=ComponentStatus.RESOLVED;
    return true;
  }
  return false;
}
