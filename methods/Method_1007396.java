public Object execute(String className,String method,Object... params) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
  LOGGER.error("Start");
  PluginManager.getInstance().initClassLoader(appClassLoader,protectionDomain);
  Class classInAppClassLoader=Class.forName(className,true,appClassLoader);
  LOGGER.error("Executing: requestedClassLoader={}, resolvedClassLoader={}, class={}, method={}, params={}",appClassLoader,classInAppClassLoader.getClassLoader(),classInAppClassLoader,method,params);
  Class[] paramTypes=new Class[params.length];
  for (int i=0; i < params.length; i++) {
    if (params[i] == null)     throw new IllegalArgumentException("Cannot execute for null parameter classNameRegexp");
 else     if (!isSimpleType(params[i].getClass())) {
      throw new IllegalArgumentException("Use only simple parameter values.");
    }
 else {
      paramTypes[i]=params[i].getClass();
    }
  }
  Object instance=classInAppClassLoader.newInstance();
  Method m=classInAppClassLoader.getDeclaredMethod(method,paramTypes);
  Thread.currentThread().setContextClassLoader(appClassLoader);
  return m.invoke(instance,params);
}
