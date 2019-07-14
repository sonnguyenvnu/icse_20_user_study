/** 
 * A utility for using the current class loader (rather than the system class loader) when instantiating a new class. <p> The idea is that the thread's current loader might have an obscure notion of what your class filePath is (e.g. an app server) that will not be captured properly by the system class loader. <p> taken from http://sourceforge.net/forum/message.php?msg_id=1720229
 * @param name class name to load
 * @return the newly loaded class
 * @throws ClassNotFoundException ClassNotFoundException
 */
public static Class<?> forName(String name) throws ClassNotFoundException {
  ClassLoader ctxLoader=null;
  try {
    ctxLoader=Thread.currentThread().getContextClassLoader();
    return Class.forName(name,true,ctxLoader);
  }
 catch (  ClassNotFoundException ex) {
  }
catch (  SecurityException ex) {
  }
  return Class.forName(name);
}
