private String generateKey(ComponentContext parentContext){
  final Component parentScope=parentContext.getComponentScope();
  final String key=getKey();
  final String globalKey;
  if (parentScope == null) {
    globalKey=key;
  }
 else {
    if (parentScope.getGlobalKey() == null) {
      final ComponentsLogger logger=parentContext.getLogger();
      if (logger != null) {
        logger.emitMessage(ComponentsLogger.LogLevel.ERROR,"Trying to generate parent-based key for component " + getSimpleName() + " , but parent " + parentScope.getSimpleName() + " has a null global key \"." + " This is most likely a configuration mistake, check the value of ComponentsConfiguration.useGlobalKeys.");
      }
      globalKey="null" + key;
    }
 else {
      globalKey=parentScope.generateUniqueGlobalKeyForChild(this,key);
    }
  }
  return globalKey;
}
