/** 
 * Opens a class of the specified name for reading using provided class loader.
 */
public static InputStream getClassAsStream(final String className,final ClassLoader classLoader) throws IOException {
  return getResourceAsStream(ClassUtil.convertClassNameToFileName(className),classLoader);
}
