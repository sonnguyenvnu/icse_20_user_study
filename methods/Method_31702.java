/** 
 * Adds these jars or directories to the classpath.
 * @param classLoader The current ClassLoader.
 * @param jarFiles    The jars or directories to add.
 * @return The new ClassLoader containing the additional jar or directory.
 */
public static ClassLoader addJarsOrDirectoriesToClasspath(ClassLoader classLoader,List<File> jarFiles){
  List<URL> urls=new ArrayList<>();
  for (  File jarFile : jarFiles) {
    LOG.debug("Adding location to classpath: " + jarFile.getAbsolutePath());
    try {
      urls.add(jarFile.toURI().toURL());
    }
 catch (    Exception e) {
      throw new FlywayException("Unable to load " + jarFile.getPath(),e);
    }
  }
  return new URLClassLoader(urls.toArray(new URL[0]),classLoader);
}
