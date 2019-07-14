/** 
 * Sets white/black list mode for jars.
 */
public ClassScanner excludeAllJars(final boolean whitelist){
  if (whitelist) {
    rulesJars.whitelist();
  }
 else {
    rulesJars.blacklist();
  }
  return this;
}
