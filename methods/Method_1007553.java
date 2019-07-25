/** 
 * run static method className.method(args) using reflection and this WeavingClassLoader 
 */
public void run(String className,String method,String... args) throws Exception {
  Class<?> mainClass=loadClass(className);
  Method mainMethod=mainClass.getMethod(method,new Class[]{String[].class});
  mainMethod.invoke(null,new Object[]{args});
}
