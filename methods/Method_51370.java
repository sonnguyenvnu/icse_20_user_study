/** 
 * Specify the allowed package prefixes.
 * @param packs The package prefixes
 * @return The same builder
 */
@SuppressWarnings("unchecked") public T legalPackageNames(String... packs){
  if (packs != null) {
    this.legalPackageNames=Arrays.copyOf(packs,packs.length);
  }
  return (T)this;
}
