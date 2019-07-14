public static HadoopCompat getCompat(){
  String ver=VersionInfo.getVersion();
  log.debug("Read Hadoop VersionInfo string {}",ver);
  final String pkgName=HadoopCompatLoader.class.getPackage().getName();
  final String className;
  if (ver.startsWith("1.")) {
    className=pkgName + ".h1.Hadoop1Compat";
  }
 else {
    className=pkgName + ".h2.Hadoop2Compat";
  }
  log.debug("Attempting to load class {} and instantiate with nullary constructor",className);
  try {
    Constructor<?> ctor=Class.forName(className).getConstructor();
    log.debug("Invoking constructor {}",ctor);
    return (HadoopCompat)ctor.newInstance();
  }
 catch (  NoSuchMethodException|SecurityException|ClassNotFoundException|InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
    throw new RuntimeException(e);
  }
}
