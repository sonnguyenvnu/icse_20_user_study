@Override public void resolvePendingResolveComponent(ComponentName componentName){
  ComponentInfo componentInfo=registry.get(componentName);
  if (componentInfo.isResolved()) {
    return;
  }
  if (componentInfo.resolve()) {
    typeRegistry(componentInfo);
    try {
      componentInfo.activate();
    }
 catch (    Throwable e) {
      componentInfo.exception(new Exception(e));
      SofaLogger.error(e,"Failed to create the component " + componentInfo.getName());
    }
  }
}
