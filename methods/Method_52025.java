/** 
 * Gets the Class instance identified by this qualified name.
 * @return A class instance
 * @throws ClassNotFoundException if the class is not found
 */
private Class<?> loadType() throws ClassNotFoundException {
  if (classLoader != null) {
    return classLoader.loadClass(getBinaryName());
  }
  return null;
}
