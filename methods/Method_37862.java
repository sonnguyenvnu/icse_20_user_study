/** 
 * Opens a class of the specified name for reading. No specific classloader is used for loading class.
 * @see #getResourceAsStream(String,ClassLoader)
 */
public static InputStream getClassAsStream(final String className) throws IOException {
  return getResourceAsStream(ClassUtil.convertClassNameToFileName(className));
}
