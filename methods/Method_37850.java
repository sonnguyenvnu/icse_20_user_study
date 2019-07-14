/** 
 * Removes the current classloader's value for this variable.
 */
public synchronized void remove(){
  ClassLoader contextClassLoader=Thread.currentThread().getContextClassLoader();
  weakMap.remove(contextClassLoader);
}
