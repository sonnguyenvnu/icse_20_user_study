/** 
 * Check whether the supplied class name exists.
 */
public boolean classNameExists(String fullyQualifiedClassName){
  try {
    pmdClassLoader.loadClass(fullyQualifiedClassName);
    return true;
  }
 catch (  ClassNotFoundException e) {
    return false;
  }
catch (  LinkageError e2) {
    return true;
  }
}
