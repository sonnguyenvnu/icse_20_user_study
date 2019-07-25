public static void process(int dbOldVersion,int dbNewVersion) throws InvocationTargetException, IllegalAccessException {
  try {
    List<Method> methodsToLaunch=getInstance().getMethodsToLaunch(dbOldVersion,dbNewVersion);
    for (    Method methodToLaunch : methodsToLaunch) {
      LogDelegate.d("Running upgrade processing method: " + methodToLaunch.getName());
      methodToLaunch.invoke(getInstance());
    }
  }
 catch (  SecurityException|IllegalAccessException|InvocationTargetException e) {
    LogDelegate.e("Explosion processing upgrade!",e);
    throw e;
  }
}
