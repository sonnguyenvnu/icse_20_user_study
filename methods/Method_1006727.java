/** 
 * Test to see if a RelativePath has been filtered out by the user.
 * @param classpathElementPath the classpath element path
 * @return true, if not filtered out
 */
private boolean filter(final String classpathElementPath){
  if (scanSpec.classpathElementFilters != null) {
    for (    final ClasspathElementFilter filter : scanSpec.classpathElementFilters) {
      if (!filter.includeClasspathElement(classpathElementPath)) {
        return false;
      }
    }
  }
  return true;
}
