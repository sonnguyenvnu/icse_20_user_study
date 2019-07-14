/** 
 * Returns <code>true</code> if some JAR file has to be accepted.
 */
protected boolean acceptJar(final File jarFile){
  String path=jarFile.getAbsolutePath();
  path=FileNameUtil.separatorsToUnix(path);
  return rulesJars.match(path);
}
