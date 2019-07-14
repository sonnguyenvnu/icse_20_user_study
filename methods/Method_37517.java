/** 
 * Sets white/black list mode for jars.
 */
public ClassScanner includeAllJars(final boolean blacklist){
  if (blacklist) {
    rulesJars.blacklist();
  }
 else {
    rulesJars.whitelist();
  }
  return this;
}
