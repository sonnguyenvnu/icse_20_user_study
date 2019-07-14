/** 
 * Resolves Java version from current version.
 */
public static int resolveJavaVersion(final int version){
  final int javaVersionNumber=SystemUtil.info().getJavaVersionNumber();
  final int platformVersion=javaVersionNumber - 8 + 52;
  return version > platformVersion ? version : platformVersion;
}
