/** 
 * Loads a class and calls <code>main()</code> in that class.
 * @param classname         the loaded class.
 * @param args              parameters passed to <code>main()</code>.
 */
public void run(String classname,String[] args) throws Throwable {
  Class<?> c=loadClass(classname);
  try {
    c.getDeclaredMethod("main",new Class<?>[]{String[].class}).invoke(null,new Object[]{args});
  }
 catch (  InvocationTargetException e) {
    throw e.getTargetException();
  }
}
