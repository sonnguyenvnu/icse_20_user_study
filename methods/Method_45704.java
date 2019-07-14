/** 
 * ????ClassLoader???????????????????ClassLoader
 * @return ClassLoader
 */
public static ClassLoader getCurrentClassLoader(){
  ClassLoader cl=Thread.currentThread().getContextClassLoader();
  if (cl == null) {
    cl=ClassLoaderUtils.class.getClassLoader();
  }
  return cl == null ? ClassLoader.getSystemClassLoader() : cl;
}
