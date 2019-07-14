/** 
 * Checks if the currently running JVM is at least compliant with provided JDK version.
 */
public boolean isAtLeastJavaVersion(final int version){
  return JAVA_VERSION_NUMBER >= version;
}
