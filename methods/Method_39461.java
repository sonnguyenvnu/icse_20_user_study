/** 
 * Enables profiles to iterate.
 */
public PropsEntries profile(final String... profiles){
  if (profiles == null) {
    return this;
  }
  for (  String profile : profiles) {
    addProfiles(profile);
  }
  return this;
}
