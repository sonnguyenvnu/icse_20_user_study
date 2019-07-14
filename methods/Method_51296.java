/** 
 * Prepend the specified classpath like string to the current ClassLoader of the configuration. If no ClassLoader is currently configured, the ClassLoader used to load the  {@link PMDConfiguration} class will be usedas the parent ClassLoader of the created ClassLoader. <p>If the classpath String looks like a URL to a file (i.e. starts with <code>file://</code>) the file will be read with each line representing an entry on the classpath.</p>
 * @param classpath The prepended classpath.
 * @throws IOException if the given classpath is invalid (e.g. does not exist)
 * @see PMDConfiguration#setClassLoader(ClassLoader)
 * @see ClasspathClassLoader
 */
public void prependClasspath(String classpath) throws IOException {
  if (classLoader == null) {
    classLoader=PMDConfiguration.class.getClassLoader();
  }
  if (classpath != null) {
    classLoader=new ClasspathClassLoader(classpath,classLoader);
  }
}
