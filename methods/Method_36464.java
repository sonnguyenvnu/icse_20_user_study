private ComponentInfo doRegister(ComponentInfo ci){
  ComponentName name=ci.getName();
  if (isRegistered(name)) {
    SofaLogger.error("Component was already registered: {0}",name);
    if (ci.canBeDuplicate()) {
      return getComponentInfo(name);
    }
    throw new ServiceRuntimeException("Component can not be registered duplicated: " + name);
  }
  try {
    ci.register();
  }
 catch (  Throwable e) {
    SofaLogger.error(e,"Failed to register component: {0}",ci.getName());
    return null;
  }
  SofaLogger.info("Registering component: {0}",ci.getName());
  try {
    ComponentInfo old=registry.putIfAbsent(ci.getName(),ci);
    if (old != null) {
      SofaLogger.error("Component was already registered: {0}",name);
      if (ci.canBeDuplicate()) {
        return old;
      }
      throw new ServiceRuntimeException("Component can not be registered duplicated: " + name);
    }
    if (ci.resolve()) {
      typeRegistry(ci);
      ci.activate();
    }
  }
 catch (  Throwable e) {
    ci.exception(new Exception(e));
    SofaLogger.error(e,"Failed to create the component {0}",ci.getName());
  }
  return ci;
}
