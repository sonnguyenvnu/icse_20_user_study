private boolean isProfileActive(String moduleProfile){
  validateProfile(moduleProfile);
  return this.activeProfiles.contains(moduleProfile.trim());
}
