private ControllerClassInfo getOrCreateTargetClass(Map<TypeElement,ControllerClassInfo> controllerClassMap,TypeElement controllerClassElement){
  if (!isController(controllerClassElement)) {
    errorLogger.logError("Class with %s annotations must extend %s (%s)",AutoModel.class.getSimpleName(),EPOXY_CONTROLLER_TYPE,controllerClassElement.getSimpleName());
  }
  ControllerClassInfo controllerClassInfo=controllerClassMap.get(controllerClassElement);
  if (controllerClassInfo == null) {
    controllerClassInfo=new ControllerClassInfo(elementUtils,controllerClassElement);
    controllerClassMap.put(controllerClassElement,controllerClassInfo);
  }
  return controllerClassInfo;
}
