/** 
 * Redefine the supplied set of classes using the supplied bytecode. This method operates on a set in order to allow interdependent changes to more than one class at the same time (a redefinition of class A can require a redefinition of class B).
 * @param reloadMap class -> new bytecode
 * @see java.lang.instrument.Instrumentation#redefineClasses(java.lang.instrument.ClassDefinition)
 */
public void hotswap(Map<Class<?>,byte[]> reloadMap){
  if (instrumentation == null) {
    throw new IllegalStateException("Plugin manager is not correctly initialized - no instrumentation available.");
  }
synchronized (reloadMap) {
    ClassDefinition[] definitions=new ClassDefinition[reloadMap.size()];
    String[] classNames=new String[reloadMap.size()];
    int i=0;
    for (    Map.Entry<Class<?>,byte[]> entry : reloadMap.entrySet()) {
      classNames[i]=entry.getKey().getName();
      definitions[i++]=new ClassDefinition(entry.getKey(),entry.getValue());
    }
    try {
      LOGGER.reload("Reloading classes {} (autoHotswap)",Arrays.toString(classNames));
synchronized (hotswapLock) {
        instrumentation.redefineClasses(definitions);
      }
      LOGGER.debug("... reloaded classes {} (autoHotswap)",Arrays.toString(classNames));
    }
 catch (    Exception e) {
      throw new IllegalStateException("Unable to redefine classes",e);
    }
    reloadMap.clear();
  }
}
