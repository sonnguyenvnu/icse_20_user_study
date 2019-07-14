/** 
 * Enables list of profiles.
 */
public void useProfiles(final String... enabledProfiles){
  if (enabledProfiles == null) {
    return;
  }
  if (this.enabledProfiles == null) {
    this.enabledProfiles=new HashSet<>();
  }
  Collections.addAll(this.enabledProfiles,enabledProfiles);
}
