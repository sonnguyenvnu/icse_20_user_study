/** 
 * Check whether the supplied class name exists.
 */
@Override public boolean classNameExists(String fullyQualifiedClassName){
  try {
    pmdClassLoader.loadClass(fullyQualifiedClassName);
    return true;
  }
 catch (  ClassNotFoundException e) {
    return false;
  }
catch (  NoClassDefFoundError e) {
    return false;
  }
}
