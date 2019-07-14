/** 
 * Sets white/black list mode for entries.
 */
public ClassScanner includeAllEntries(final boolean blacklist){
  if (blacklist) {
    rulesEntries.blacklist();
  }
 else {
    rulesEntries.whitelist();
  }
  return this;
}
