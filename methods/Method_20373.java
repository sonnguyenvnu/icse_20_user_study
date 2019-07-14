private void generateJava(Map<TypeElement,ControllerClassInfo> controllerClassMap){
  for (  Entry<TypeElement,ControllerClassInfo> controllerInfo : controllerClassMap.entrySet()) {
    try {
      generateHelperClassForController(controllerInfo.getValue());
    }
 catch (    Exception e) {
      errorLogger.logError(e);
    }
  }
}
