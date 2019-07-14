/** 
 * Returns default class loader. By default, it is  {@link #getContextClassLoader() threads context class loader}. If this one is <code>null</code>, then class loader of the <b>caller class</b> is returned.
 */
public static ClassLoader getDefaultClassLoader(){
  ClassLoader cl=getContextClassLoader();
  if (cl == null) {
    Class callerClass=ClassUtil.getCallerClass(2);
    cl=callerClass.getClassLoader();
  }
  return cl;
}
