/** 
 * run the calling (static) method in the WeavingClassLoader context using the provided config
 * @param config the config
 * @param args the args to pass to the method
 * @return true if the trampoline was called and returned
 */
public static boolean trampoline(Config config,String... args){
  Object example=config.example;
  StackTraceElement ste=new Exception().getStackTrace()[config.offset];
  Class klass=example == null ? null : example instanceof Class ? (Class)example : example.getClass();
  ClassLoader ecl=klass == null ? null : klass.getClassLoader();
  ClassLoader cl=ecl == null ? Kilim.class.getClassLoader() : ecl;
  if (cl.getClass().getName().equals(WeavingClassLoader.class.getName()))   return false;
  try {
    if (config.check) {
      try {
        Class check=cl.loadClass(ste.getClassName());
        if (isWoven(check))         return false;
      }
 catch (      ClassNotFoundException ex) {
      }
    }
    WeavingClassLoader loader=new WeavingClassLoader(ecl,null);
    if (config.exclude != null)     loader.exclude(config.exclude);
    loader.run(ste.getClassName(),ste.getMethodName(),args);
  }
 catch (  RuntimeException ex) {
    throw ex;
  }
catch (  Exception ex) {
    throw new RuntimeException(ex);
  }
  return true;
}
