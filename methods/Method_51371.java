/** 
 * Specify the allowed package prefixes.
 * @param packs The package prefixes
 * @return The same builder
 */
@SuppressWarnings("unchecked") public T legalPackageNames(Collection<String> packs){
  if (packs != null) {
    this.legalPackageNames=packs.toArray(new String[0]);
  }
  return (T)this;
}
