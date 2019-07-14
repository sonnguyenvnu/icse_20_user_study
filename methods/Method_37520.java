/** 
 * Sets white/black list mode for entries.
 */
public ClassScanner excludeAllEntries(final boolean whitelist){
  if (whitelist) {
    rulesEntries.whitelist();
  }
 else {
    rulesEntries.blacklist();
  }
  return this;
}
