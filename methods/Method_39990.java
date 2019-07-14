/** 
 * Enables single profile.
 */
public void useProfile(final String profile){
  if (profile == null) {
    return;
  }
  if (this.enabledProfiles == null) {
    this.enabledProfiles=new HashSet<>();
  }
  this.enabledProfiles.add(profile);
}
