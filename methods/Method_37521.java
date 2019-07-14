/** 
 * Sets excluded names that narrows included set of packages.
 * @see InExRules
 */
public ClassScanner excludeEntries(final String... excludedEntries){
  for (  final String excludedEntry : excludedEntries) {
    rulesEntries.exclude(excludedEntry);
  }
  return this;
}
